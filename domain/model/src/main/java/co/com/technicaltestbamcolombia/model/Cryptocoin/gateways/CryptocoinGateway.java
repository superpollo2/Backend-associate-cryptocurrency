package co.com.technicaltestbamcolombia.model.Cryptocoin.gateways;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CryptocoinGateway {
    Flux<CryptocoinDTO> findCryptocoinByUserId(Integer userId);
    Flux<CryptocoinDTO> findCryptocoinByCountryId(Integer countryId);
    Mono<UserCryptocoinDTO> saveAssociateCoin(UserCryptocoinDTO userCryptocoinDTO);
    Mono<Void> deleteCoinFromUser(UserCryptocoinDTO userCryptocoinDTO);
    Mono<UserCryptocoinDTO> editAmountCoinFromUser(UserCryptocoinDTO userCryptocoinDTO);
}
