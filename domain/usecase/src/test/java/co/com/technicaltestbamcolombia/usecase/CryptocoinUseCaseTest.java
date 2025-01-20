package co.com.technicaltestbamcolombia.usecase;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.gateways.CryptocoinGateway;
import co.com.technicaltestbamcolombia.model.config.CryptoException;
import co.com.technicaltestbamcolombia.usecase.providers.TestBuilders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CryptocoinUseCaseTest {

    @Mock
    private CryptocoinGateway cryptocoinGateway;

    @InjectMocks
    private CryptocoinUseCase cryptocoinUseCase;

    @Test
    void getAllCryptocoinsavailableShouldReturnCryptocoins() {

        Integer userId = 1;
        var list = TestBuilders.getCryptocoinDTOList();
        Mockito.when(cryptocoinGateway.findCryptocoinByUserId(userId))
                .thenReturn(Flux.just(list.get(0), list.get(1)));

        var result = cryptocoinUseCase.getAllCryptocoinsavailable(userId);

        StepVerifier.create(result)
                .expectNext(list.get(0))
                .expectNext(list.get(1))
                .verifyComplete();
        Mockito.verify(cryptocoinGateway, times(1)).findCryptocoinByUserId(userId);
    }

   /* @Test
    void associateCoinToUserShouldAssociateCoin() {

        var requestBody = TestBuilders.getUserCryptocoinDTOWitAmount();
        Mockito.when(cryptocoinGateway.saveAssociateCoin(any(UserCryptocoinDTO.class))).thenReturn(Mono.just(requestBody));


        var result = cryptocoinUseCase.associateCoinToUser(requestBody);

        StepVerifier.create(result)
                .expectNext(requestBody)
                .verifyComplete();
        Mockito.verify(cryptocoinGateway, times(1)).saveAssociateCoin(requestBody);
    }*/


    @Test
    void associateCoinToUser_ShouldReturnErrorWhenCoinAlreadyAssociated() {
        // Arrange
        var errorTitle = "ERROR CONSULTA, NO EXISTE O NO ESTA DISPONIBLE";
        var request = TestBuilders.getUserCryptocoinDTOWitAmount();
        when(cryptocoinGateway.saveAssociateCoin(request)).thenReturn(Mono.just(request));
        var result = cryptocoinUseCase.associateCoinToUser(request);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof CryptoException &&
                                ((CryptoException) throwable).getStatus() == 404 &&
                                "CR-B000".equals(((CryptoException) throwable).getCode()) &&
                                errorTitle.equals(((CryptoException) throwable).getTitle()) &&
                                "La moneda ya se encuentra asociada al usuario".equals((throwable.getMessage()))
                )
                .verify();
        Mockito.verify(cryptocoinGateway).saveAssociateCoin(request);
    }

    @Test
    void cryptocoinsavailableCryptoinsByCountries_ShouldReturnCryptocoins() {

        int countryId = 1;
        var list = TestBuilders.getCryptocoinDTOList();
        Mockito.when(cryptocoinGateway.findCryptocoinByCountryId(countryId))
                .thenReturn(Flux.just(list.get(0), list.get(1)));

        Flux<CryptocoinDTO> result = cryptocoinUseCase.cryptocoinsavailableCryptoinsByCountries(countryId);

        StepVerifier.create(result)
                .expectNext(list.get(0))
                .expectNext(list.get(1))
                .verifyComplete();
        Mockito.verify(cryptocoinGateway).findCryptocoinByCountryId(countryId);
    }

    @Test
    void deleteCoinFromUser_ShouldDeleteCoin() {

        var request = TestBuilders.getUserCryptocoinDTOWitAmount();
        Mockito.when(cryptocoinGateway.deleteCoinFromUser(request))
                .thenReturn(Mono.empty());

        Mono<Void> result = cryptocoinUseCase.deleteCoinFromUser(request);


        StepVerifier.create(result)
                .verifyComplete();
        Mockito.verify(cryptocoinGateway).deleteCoinFromUser(request);
    }

    @Test
    void editAmounCoinFromUser_ShouldEditAmount() {

        var request = TestBuilders.getUserCryptocoinDTOWitAmount();
        Mockito.when(cryptocoinGateway.editAmountCoinFromUser(request))
                .thenReturn(Mono.just(request));

        var result = cryptocoinUseCase.editAmounCoinFromUser(request);

        StepVerifier.create(result)
                .expectNext(request)
                .verifyComplete();
        Mockito.verify(cryptocoinGateway).editAmountCoinFromUser(request);
    }
}