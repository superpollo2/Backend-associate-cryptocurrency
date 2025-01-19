package co.com.technicaltestbamcolombia.model.Cryptocoin.gateways;

import co.com.technicaltestbamcolombia.model.Cryptocoin.Cryptocoin;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CryptocoinGateway {
    Flux<Cryptocoin> findByCryptocoinId(Integer cryptocoinId);
    Flux<Cryptocoin> findCryptocoinByUserId(Integer userId);
    Flux<Cryptocoin> findCryptocoinByCountryId(Integer countryId);
    Mono<UserCryptocoin> saveAssociateCoin(UserCryptocoin userCryptocoin);
}
