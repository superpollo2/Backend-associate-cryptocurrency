package co.com.technicaltestbamcolombia.api.service;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinUserAmountDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.CrytocoinCountryDTO;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import co.com.technicaltestbamcolombia.usecase.CryptocoinUseCase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ApiRestService {
    private final CryptocoinUseCase cryptocoinUseCase;
    private final ObjectMapper objectMapper;

    public Flux<CryptocoinUserAmountDTO> getAllAvailableCryptocoins(Integer userId) {
        return cryptocoinUseCase.getAllCryptocoinsavailable(userId);

    }

    public Mono<CryptocoinUserAmountDTO> associateCoinToUser(JsonNode body) {
        return cryptocoinUseCase.associateCoinToUser(objectMapper.convertValue(body,UserCryptocoinDTO.class));

    }


    public Mono<CrytocoinCountryDTO>  availableCryptoinsByCountries(int countryId) {
        return cryptocoinUseCase.cryptocoinsavailableCryptoinsByCountries(countryId);

    }

    public Mono<Void> deleteCoinFromUser(JsonNode body){
        return cryptocoinUseCase.deleteCoinFromUser(objectMapper.convertValue(body, UserCryptocoinDTO.class));
    }

    public Mono<UserCryptocoinDTO> editAmounCoinFromUser(JsonNode body) {
        return cryptocoinUseCase.editAmounCoinFromUser(objectMapper.convertValue(body,UserCryptocoinDTO.class));
    }


}
