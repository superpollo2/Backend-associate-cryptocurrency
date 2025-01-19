package co.com.technicaltestbamcolombia.usecase;

import co.com.technicaltestbamcolombia.model.Cryptocoin.Cryptocoin;
import co.com.technicaltestbamcolombia.model.Cryptocoin.gateways.CryptocoinGateway;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoin;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class CryptocoinUseCase {

    private final CryptocoinGateway cryptocoinGateway;

    public Flux<Cryptocoin> getAllCryptocoinsavailable(Integer userId) {
        return cryptocoinGateway.findCryptocoinByUserId(userId);
    }

    public Mono<UserCryptocoin> associateCoinToUser(UserCryptocoin userCryptocoin) {
        return cryptocoinGateway.saveAssociateCoin(userCryptocoin);
    }

    public Flux<Cryptocoin> cryptocoinsavailableCryptoinsByCountries(int countryId) {
        return cryptocoinGateway.findCryptocoinByCountryId(countryId);
    }

}
