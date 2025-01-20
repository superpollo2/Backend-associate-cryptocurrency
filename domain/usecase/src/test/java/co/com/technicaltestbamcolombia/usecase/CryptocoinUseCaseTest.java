package co.com.technicaltestbamcolombia.usecase;

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