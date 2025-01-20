package co.com.technicaltestbamcolombia.usecase;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinUserAmountDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.CrytocoinCountryDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.gateways.CryptocoinGateway;
import co.com.technicaltestbamcolombia.model.config.CryptoErrorCode;
import co.com.technicaltestbamcolombia.model.config.CryptoException;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CryptocoinUseCase {

    private final CryptocoinGateway cryptocoinGateway;

    public Flux<CryptocoinUserAmountDTO> getAllCryptocoinsavailable(Integer userId) {
        return cryptocoinGateway.findCryptocoinByUserId(userId);
    }

    public Mono<CryptocoinUserAmountDTO> associateCoinToUser(UserCryptocoinDTO userCryptocoinDTO) {
        return cryptocoinGateway.saveAssociateCoin(userCryptocoinDTO);
    }

    public Mono<CrytocoinCountryDTO>  cryptocoinsavailableCryptoinsByCountries(int countryId) {
        return cryptocoinGateway.findCryptocoinByCountryId(countryId);
    }

    public Mono<Void> deleteCoinFromUser(UserCryptocoinDTO userCryptocoinDTO) {
        return cryptocoinGateway.deleteCoinFromUser(userCryptocoinDTO);
    }

    public Mono<UserCryptocoinDTO> editAmounCoinFromUser(UserCryptocoinDTO userCryptocoinDTO) {

        return cryptocoinGateway.editAmountCoinFromUser(userCryptocoinDTO);
    }

}
