package co.com.technicaltestbamcolombia.r2dbc.service;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.gateways.CryptocoinGateway;
import co.com.technicaltestbamcolombia.model.config.CryptoErrorCode;
import co.com.technicaltestbamcolombia.model.config.CryptoException;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import co.com.technicaltestbamcolombia.r2dbc.mapper.MapperEntity;
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
    private final MapperEntity mapperEntity;
    private final UserService userService;


    @Override
    public Flux<CryptocoinDTO> findCryptocoinByUserId(Integer userId) {
        return cryptocoinCustomRepository.findCryptocoinsByUserId(userId)
                .switchIfEmpty(Flux.defer(() -> {
                    throw new CryptoException(HttpStatus.NOT_FOUND.value(), CryptoErrorCode.CRB00.getErrorCode()
                            ,CryptoErrorCode.CRB00.getErrorTitle() , "No hay Cryptomonedas para el usuario:" + userId);
                }));
    }

    @Override
    public Flux<CryptocoinDTO> findCryptocoinByCountryId(Integer countryId) {
        return cryptocoinCustomRepository.findCryptocoinByCountryId(countryId)
                .switchIfEmpty(Flux.defer(() -> {
                    throw new CryptoException(HttpStatus.NOT_FOUND.value(), CryptoErrorCode.CRB00.getErrorCode()
                            ,CryptoErrorCode.CRB00.getErrorTitle() , "No hay Cryptomonedas para el país :" + countryId);
                }));
    }

    @Override
    public Mono<UserCryptocoinDTO> saveAssociateCoin(UserCryptocoinDTO userCryptocoinDTO) {

        return userService.findCountryByUserId(userCryptocoinDTO.getUserId())
                .flatMap(countryId ->
                        findCryptocoinByCountryId(countryId)
                                .filter(cryptocoin -> cryptocoin.getCryptocoinId().equals(userCryptocoinDTO.getCryptocoinId()))
                                .hasElements()
                                .flatMap(isAvailable -> {
                                    if (isAvailable) {
                                        return userCryptocoinRepository.findByCryptocoinIdAndUserId(userCryptocoinDTO.getCryptocoinId(), userCryptocoinDTO.getUserId())
                                                .flatMap(existingEntity -> Mono.just(userCryptocoinDTO))
                                                .switchIfEmpty(
                                                        Mono.defer(() -> {
                                                            var entity = mapperEntity.toEntity(userCryptocoinDTO);
                                                            return userCryptocoinRepository.save(entity)
                                                                    .map(mapperEntity::toDomain);
                                                        })
                                                );
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
                                CryptoErrorCode.CRB01.getErrorCode(),
                                CryptoErrorCode.CRB01.getErrorTitle(),
                                "No existe información o el valor del amount da un saldo negativo"
                        ));
                    }
                    return userCryptocoinRepository.findByCryptocoinIdAndUserId(
                            userCryptocoinDTO.getCryptocoinId(),
                            userCryptocoinDTO.getUserId()
                    );
                })
                .map(mapperEntity::toDomain);
    }
}
