package co.com.technicaltestbamcolombia.api.service;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinUserAmountDTO;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import co.com.technicaltestbamcolombia.usecase.CryptocoinUseCase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApiRestServiceTest {

    @Mock
    private CryptocoinUseCase cryptocoinUseCase;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ApiRestService apiRestService;

    @Test
    void testGetAllAvailableCryptocoins() {
        var userId = 1;
        var cryptocoinDTO = new CryptocoinUserAmountDTO();
        when(cryptocoinUseCase.getAllCryptocoinsavailable(userId))
                .thenReturn(Flux.just(cryptocoinDTO));

        StepVerifier.create(apiRestService.getAllAvailableCryptocoins(userId))
                .expectNext(cryptocoinDTO)
                .verifyComplete();

        verify(cryptocoinUseCase, times(1)).getAllCryptocoinsavailable(userId);
    }

  /*  @Test
    void testAssociateCoinToUser() {
        var body = mock(JsonNode.class);
        var userCryptocoinDTO = new UserCryptocoinDTO();
        when(objectMapper.convertValue(body, UserCryptocoinDTO.class)).thenReturn(userCryptocoinDTO);
        when(cryptocoinUseCase.associateCoinToUser(userCryptocoinDTO)).thenReturn(Mono.just(userCryptocoinDTO));

        StepVerifier.create(apiRestService.associateCoinToUser(body))
                .expectNext(userCryptocoinDTO)
                .verifyComplete();

        verify(objectMapper, times(1)).convertValue(body, UserCryptocoinDTO.class);
        verify(cryptocoinUseCase, times(1)).associateCoinToUser(userCryptocoinDTO);
    }*/

   /*@Test
    void testAvailableCryptoinsByCountries() {
        var countryId = 1;
        var cryptocoinDTO = new CrytocoinCountryDTO();
        when(cryptocoinUseCase.cryptocoinsavailableCryptoinsByCountries(countryId))
                .thenReturn(Flux.just(cryptocoinDTO));

        StepVerifier.create(apiRestService.availableCryptoinsByCountries(countryId))
                .expectNext(cryptocoinDTO)
                .verifyComplete();

        verify(cryptocoinUseCase, times(1)).cryptocoinsavailableCryptoinsByCountries(countryId);
    }*/

    @Test
    void testDeleteCoinFromUser() {
        var body = mock(JsonNode.class);
        var userCryptocoinDTO = new UserCryptocoinDTO();
        when(objectMapper.convertValue(body, UserCryptocoinDTO.class)).thenReturn(userCryptocoinDTO);
        when(cryptocoinUseCase.deleteCoinFromUser(userCryptocoinDTO)).thenReturn(Mono.empty());

        StepVerifier.create(apiRestService.deleteCoinFromUser(body))
                .verifyComplete();

        verify(objectMapper, times(1)).convertValue(body, UserCryptocoinDTO.class);
        verify(cryptocoinUseCase, times(1)).deleteCoinFromUser(userCryptocoinDTO);
    }

    @Test
    void testEditAmounCoinFromUser() {
        var body = mock(JsonNode.class);
        var  userCryptocoinDTO = new UserCryptocoinDTO();
        when(objectMapper.convertValue(body, UserCryptocoinDTO.class)).thenReturn(userCryptocoinDTO);
        when(cryptocoinUseCase.editAmounCoinFromUser(userCryptocoinDTO)).thenReturn(Mono.just(userCryptocoinDTO));

        StepVerifier.create(apiRestService.editAmounCoinFromUser(body))
                .expectNext(userCryptocoinDTO)
                .verifyComplete();

        verify(objectMapper, times(1)).convertValue(body, UserCryptocoinDTO.class);
        verify(cryptocoinUseCase, times(1)).editAmounCoinFromUser(userCryptocoinDTO);
    }
}