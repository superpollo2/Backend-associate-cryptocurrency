package co.com.technicaltestbamcolombia.r2dbc.service;

import co.com.technicaltestbamcolombia.model.Cryptocoin.Cryptocoin;
import co.com.technicaltestbamcolombia.model.Cryptocoin.gateways.CryptocoinGateway;
import co.com.technicaltestbamcolombia.model.config.CryptoErrorCode;
import co.com.technicaltestbamcolombia.model.config.CryptoException;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoin;
import co.com.technicaltestbamcolombia.r2dbc.mapper.MapperEntity;
import co.com.technicaltestbamcolombia.r2dbc.repository.CryptocoinCustomRepository;
import co.com.technicaltestbamcolombia.r2dbc.repository.UserCryptocoinRepository;
import io.netty.handler.codec.http.HttpStatusClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CryptocoinService implements CryptocoinGateway {

    private final CryptocoinCustomRepository cryptocoinCustomRepository;
    private final UserCryptocoinRepository userCryptocoinRepository;
    private final MapperEntity mapperEntity;
    private final UserService userService;


    @Override
    public Flux<Cryptocoin> findByCryptocoinId(Integer cryptocoinId) {
        return null;
    }

    @Override
    public Flux<Cryptocoin> findCryptocoinByUserId(Integer userId) {
        return cryptocoinCustomRepository.findCryptocoinsByUserId(userId);
    }

    @Override
    public Flux<Cryptocoin> findCryptocoinByCountryId(Integer countryId) {
        return cryptocoinCustomRepository.findCryptocoinByCountryId(countryId);
    }

    @Override
    public Mono<UserCryptocoin> saveAssociateCoin(UserCryptocoin userCryptocoin) {

        return userService.findCountryByUserId(userCryptocoin.getUserId())
                .flatMap(countryId -> findCryptocoinByCountryId(countryId)
                        .filter(cryptocoin -> cryptocoin.getCryptocoinId().equals(userCryptocoin.getCryptocoinId()))
                        .hasElements()
                        .flatMap(isAvailable -> {
                            if (isAvailable) {
                                var entity = mapperEntity.toEntity(userCryptocoin);
                                return userCryptocoinRepository.save(entity)
                                        .map(mapperEntity::toDomain);
                            } else {
                               throw new CryptoException(HttpStatus.BAD_REQUEST.value(),
                                       CryptoErrorCode.CRB000.getErrorCode(), CryptoErrorCode.CRB000.getErrorTitle(),
                                       "La moneda no esta permitida para el país en el reside el usuario");
                            }
                        }));
        }
}
