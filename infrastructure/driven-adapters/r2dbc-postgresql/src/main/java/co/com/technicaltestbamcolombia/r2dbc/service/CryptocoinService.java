package co.com.technicaltestbamcolombia.r2dbc.service;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinUserAmountDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.CrytocoinCountryDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.gateways.CryptocoinGateway;
import co.com.technicaltestbamcolombia.model.config.CryptoErrorCode;
import co.com.technicaltestbamcolombia.model.config.CryptoException;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import co.com.technicaltestbamcolombia.r2dbc.mapper.Mapper;
import co.com.technicaltestbamcolombia.r2dbc.repository.CryptocoinCustomRepository;
import co.com.technicaltestbamcolombia.r2dbc.repository.UserCryptocoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log
@RequiredArgsConstructor
public class CryptocoinService implements CryptocoinGateway {

    private final CryptocoinCustomRepository cryptocoinCustomRepository;
    private final UserCryptocoinRepository userCryptocoinRepository;
    private final Mapper mapper;
    private final UserService userService;
    private final CountryService countryService;


    @Override
    public Flux<CryptocoinUserAmountDTO> findCryptocoinByUserId(Integer userId) {
        return cryptocoinCustomRepository.findCryptocoinsByUserId(userId)
                .switchIfEmpty(Flux.defer(() -> {
                    throw new CryptoException(HttpStatus.NOT_FOUND.value(), CryptoErrorCode.CRB00.getErrorCode()
                            ,CryptoErrorCode.CRB00.getErrorTitle() , "No hay Cryptomonedas para el usuario: " + userId);
                }));
    }

    @Override
    public Mono<CrytocoinCountryDTO> findCryptocoinByCountryId(Integer userId) {
        var countryMonor = countryService.getCountry(12);
        log.info(countryMonor.toString());
        return userService.findCountryByUserId(userId)
                .flatMap(countryId -> {
                    var countryMono = countryService.getCountry(countryId);
                    var cryptocoinsMono = cryptocoinCustomRepository.findCryptocoinByCountryId(countryId)
                            .collectList()
                            .switchIfEmpty(Mono.error(() -> new CryptoException(
                                    HttpStatus.NOT_FOUND.value(),
                                    CryptoErrorCode.CRB00.getErrorCode(),
                                    CryptoErrorCode.CRB00.getErrorTitle(),
                                    "No se encontró el país con ID: " + countryId
                            )));

                    return Mono.zip(countryMono, cryptocoinsMono)
                            .map(tuple -> {
                                // Construir el CrytocoinCountryDTO
                                CrytocoinCountryDTO dto = new CrytocoinCountryDTO();
                                dto.setCountryDTO(tuple.getT1());
                                dto.setCryptocoins(tuple.getT2());
                                return dto;
                            });
                })
                .switchIfEmpty(Mono.error(() -> new CryptoException(
                        HttpStatus.NOT_FOUND.value(),
                        CryptoErrorCode.CRB00.getErrorCode(),
                        CryptoErrorCode.CRB00.getErrorTitle(),
                        "El usuario con ID: " + userId + " no tiene un país asociado"
                )));
    }

    @Override
    public Flux<CryptocoinDTO> findCryptocoinByCountry(Integer countryId) {
        return cryptocoinCustomRepository.findCryptocoinByCountryId(countryId)
                .switchIfEmpty(Flux.defer(() -> {
                    throw new CryptoException(HttpStatus.NOT_FOUND.value(), CryptoErrorCode.CRB00.getErrorCode()
                            ,CryptoErrorCode.CRB00.getErrorTitle() , "No hay Cryptomonedas para el país: " + countryId);
                }));
    }

    @Override
    public Mono<CryptocoinUserAmountDTO> saveAssociateCoin(UserCryptocoinDTO userCryptocoinDTO) {
        return userService.findCountryByUserId(userCryptocoinDTO.getUserId())
                .flatMap(countryId -> validateCryptocoinForCountry(countryId, userCryptocoinDTO)
                        .flatMap(isAvailable -> {
                            if (isAvailable) {
                                return checkExistingAssociationOrSave(userCryptocoinDTO);
                            } else {
                                return Mono.error(new CryptoException(
                                        HttpStatus.BAD_REQUEST.value(),
                                        CryptoErrorCode.CRB00.getErrorCode(),
                                        CryptoErrorCode.CRB00.getErrorTitle(),
                                        "La moneda no está permitida para el país en el que reside el usuario"
                                ));
                            }
                        })
                );
    }

    private Mono<Boolean> validateCryptocoinForCountry(Integer countryId, UserCryptocoinDTO userCryptocoinDTO) {
        return findCryptocoinByCountry(countryId)
                .filter(cryptocoin -> cryptocoin.getCryptocoinId().equals(userCryptocoinDTO.getCryptocoinId()))
                .hasElements();
    }

    private Mono<CryptocoinUserAmountDTO> checkExistingAssociationOrSave(UserCryptocoinDTO userCryptocoinDTO) {
        return userCryptocoinRepository.findByCryptocoinIdAndUserId(
                        userCryptocoinDTO.getCryptocoinId(), userCryptocoinDTO.getUserId())
                .flatMap(existingEntity -> {
                    return Mono.<CryptocoinUserAmountDTO>error(new CryptoException(
                            HttpStatus.CONFLICT.value(),
                            CryptoErrorCode.CRB01.getErrorCode(),
                            CryptoErrorCode.CRB01.getErrorTitle(),
                            "Ya existe una asociación entre el usuario y la criptomoneda"
                    ));
                })
                .switchIfEmpty(
                        Mono.defer(() -> {
                            var entity = mapper.toEntity(userCryptocoinDTO);
                            return userCryptocoinRepository.save(entity)
                                    .map(mapper::toDomain)
                                    .flatMap(savedEntity -> cryptocoinCustomRepository.findOneCryptocoinsByUserId(
                                            savedEntity.getUserId(), savedEntity.getCryptocoinId()));
                        })
                );
    }




    @Override
    public Mono<Void> deleteCoinFromUser(UserCryptocoinDTO userCryptocoinDTO) {
        return userCryptocoinRepository.findByCryptocoinIdAndUserId(userCryptocoinDTO.getCryptocoinId(), userCryptocoinDTO.getUserId())
                .flatMap(userCryptocoinEntity -> userCryptocoinRepository.deleteUserCryptocoinEntityByCryptocoinIdAndUserId(
                        userCryptocoinDTO.getCryptocoinId(),
                        userCryptocoinDTO.getUserId()
                ).then(Mono.just(userCryptocoinEntity)))
                .switchIfEmpty(Mono.error(new CryptoException(
                        HttpStatus.NOT_FOUND.value(),
                        CryptoErrorCode.CRB00.getErrorCode(),
                        CryptoErrorCode.CRB00.getErrorTitle(),
                        "No se encontró la información"
                ))).then();
    }

    @Override
    public Mono<UserCryptocoinDTO> editAmountCoinFromUser(UserCryptocoinDTO userCryptocoinDTO) {
        return cryptocoinCustomRepository.updateAmountCointUser(userCryptocoinDTO)
                .flatMap(updatedRows -> {
                    if (updatedRows == 0) {
                        return Mono.error(new CryptoException(
                                HttpStatus.NOT_FOUND.value(),
                                CryptoErrorCode.CRB00.getErrorCode(),
                                CryptoErrorCode.CRB00.getErrorTitle(),
                                "No existe información o el valor del amount da un saldo negativo"
                        ));
                    }
                    return userCryptocoinRepository.findByCryptocoinIdAndUserId(
                            userCryptocoinDTO.getCryptocoinId(),
                            userCryptocoinDTO.getUserId()
                    );
                })
                .map(mapper::toDomain);
    }
}
