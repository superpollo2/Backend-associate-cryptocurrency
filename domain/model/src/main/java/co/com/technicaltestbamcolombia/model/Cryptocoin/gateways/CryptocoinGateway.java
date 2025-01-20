package co.com.technicaltestbamcolombia.model.Cryptocoin.gateways;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinUserAmountDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.CrytocoinCountryDTO;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CryptocoinGateway {
    Flux<CryptocoinUserAmountDTO> findCryptocoinByUserId(Integer userId);
    Mono<CryptocoinUserAmountDTO> saveAssociateCoin(UserCryptocoinDTO userCryptocoinDTO);
    Mono<CrytocoinCountryDTO> findCryptocoinByCountryId(Integer userId);
    Mono<Void> deleteCoinFromUser(UserCryptocoinDTO userCryptocoinDTO);
    Mono<UserCryptocoinDTO> editAmountCoinFromUser(UserCryptocoinDTO userCryptocoinDTO);
    Flux<CryptocoinDTO> findCryptocoinByCountry(Integer countryId);
}
