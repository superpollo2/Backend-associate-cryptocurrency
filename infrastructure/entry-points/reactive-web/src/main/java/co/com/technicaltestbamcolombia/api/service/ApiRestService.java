package co.com.technicaltestbamcolombia.api.service;

import co.com.technicaltestbamcolombia.model.Cryptocoin.Cryptocoin;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoin;
import co.com.technicaltestbamcolombia.usecase.CryptocoinUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ApiRestService {
    private final CryptocoinUseCase cryptocoinUseCase;

    public Flux<Cryptocoin> getAllAvailableCryptocoins(Integer userId) {
        return cryptocoinUseCase.getAllCryptocoinsavailable(userId);

    }

    public Mono<UserCryptocoin> associateCoinToUser(UserCryptocoin userCryptocoin) {
        return cryptocoinUseCase.associateCoinToUser(userCryptocoin);

    }


    public Flux<Cryptocoin> availableCryptoinsByCountries(int countryId) {
        return cryptocoinUseCase.cryptocoinsavailableCryptoinsByCountries(countryId);

    }
}
