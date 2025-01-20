package co.com.technicaltestbamcolombia.r2dbc.repository;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CryptocoinCustomRepository {
    Flux<CryptocoinDTO> findCryptocoinsByUserId(Integer userId);
    Flux<CryptocoinDTO> findCryptocoinByCountryId(Integer countryId);
    Mono<Long> updateAmountCointUser(UserCryptocoinDTO userCryptocoinDTO);
}
