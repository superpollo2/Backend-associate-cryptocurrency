package co.com.technicaltestbamcolombia.usecase;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
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

    public Flux<CryptocoinDTO> getAllCryptocoinsavailable(Integer userId) {
        return cryptocoinGateway.findCryptocoinByUserId(userId);
    }

    public Mono<UserCryptocoinDTO> associateCoinToUser(UserCryptocoinDTO userCryptocoinDTO) {
        return cryptocoinGateway.saveAssociateCoin(userCryptocoinDTO)
                .flatMap(cryptocoin -> {
                    if (cryptocoin.equals(userCryptocoinDTO)) {
                        return Mono.error(new CryptoException(
                                404,
                                CryptoErrorCode.CRB00.getErrorCode(),
                                CryptoErrorCode.CRB00.getErrorTitle(),
                                "La moneda ya se encuentrA asociada al usuario"
                        ));

                    }
                    return Mono.just(cryptocoin);
                });
    }

    public Flux<CryptocoinDTO> cryptocoinsavailableCryptoinsByCountries(int countryId) {
        return cryptocoinGateway.findCryptocoinByCountryId(countryId);
    }

    public Mono<Void> deleteCoinFromUser(UserCryptocoinDTO userCryptocoinDTO) {
        return cryptocoinGateway.deleteCoinFromUser(userCryptocoinDTO);
    }

    public Mono<UserCryptocoinDTO> editAmounCoinFromUser(UserCryptocoinDTO userCryptocoinDTO) {

        return cryptocoinGateway.editAmountCoinFromUser(userCryptocoinDTO);
    }

}
