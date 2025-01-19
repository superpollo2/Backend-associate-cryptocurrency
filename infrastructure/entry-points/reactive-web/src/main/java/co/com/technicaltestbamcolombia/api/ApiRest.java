package co.com.technicaltestbamcolombia.api;

import co.com.technicaltestbamcolombia.api.service.ApiRestService;
import co.com.technicaltestbamcolombia.model.Cryptocoin.Cryptocoin;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoin;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE )
@AllArgsConstructor

public class ApiRest {

    private final ApiRestService apiRestService;

    @PostMapping
    public String soy(){
        return "Hello World";
    }

    @PostMapping(path = "/auth")
    public String soydos(){
        return "Hello World auth";
    }

    @GetMapping( path = "/country/{countryId}/coins")
    public Flux<Cryptocoin> getAvailableCryptoinsByCountries(@PathVariable("countryId") int countryId ) {
        return apiRestService.availableCryptoinsByCountries(countryId);
    }

    @GetMapping( path = "/user/{userId}/coins")
    public Flux<Cryptocoin> getAllCryptocoinsUser(@PathVariable("userId") int userId){
        return apiRestService.getAllAvailableCryptocoins(userId);
    }

    @PostMapping( path = "/user/associate-coin")
    public Mono<UserCryptocoin> associateCoinToUser(@RequestBody UserCryptocoin userCryptocoin){
        return apiRestService.associateCoinToUser(userCryptocoin);
    }
}