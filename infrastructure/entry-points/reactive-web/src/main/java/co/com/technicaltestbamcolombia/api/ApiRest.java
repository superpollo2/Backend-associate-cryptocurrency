package co.com.technicaltestbamcolombia.api;

import co.com.technicaltestbamcolombia.api.config.util.JsonSchemaValidator;
import co.com.technicaltestbamcolombia.api.service.ApiRestService;
import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinUserAmountDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.CrytocoinCountryDTO;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Log4j2
public class ApiRest {

    private final ApiRestService apiRestService;
    private final JsonSchemaValidator jsonSchemaValidator;




    @GetMapping(path = "/country/{userId}/coins-availables")
    public Mono<CrytocoinCountryDTO>  getAvailableCryptoinsByCountries(@PathVariable("userId") int userId) {
        var startTime = LocalDateTime.now();
        log.info("Iniciando consulta");
        return apiRestService.availableCryptoinsByCountries(userId)
                .doFinally(siganType -> {
                    Duration duration = Duration.between(startTime, LocalDateTime.now());
                    log.info("consulta monedas disponibles por país. Duración total: {} segundos", duration.getSeconds());
                });
    }

    @GetMapping(path = "/user/{userId}/coins")
    public Flux<CryptocoinUserAmountDTO> getAllCryptocoinsUser(@PathVariable("userId") int userId) {
        var startTime = LocalDateTime.now();
        log.info("Iniciando consulta");
        return apiRestService.getAllAvailableCryptocoins(userId)
                .doFinally(siganType -> {
                    Duration duration = Duration.between(startTime, LocalDateTime.now());
                    log.info("consulta monedas disponibles por usuario. Duración total: {} segundos", duration.getSeconds());
                });
    }



    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/user/associate-coin")
    public Mono<CryptocoinUserAmountDTO> associateCoinToUser(@RequestBody JsonNode body) {
        var init = Instant.now();
        log.info("eso " + body);
        return Mono.just(body)
                .doFirst(() -> log.info("Iniciando consulta"))
                .doOnNext(jsonSchemaValidator::validateWithJsonSchema)
                .flatMap(apiRestService::associateCoinToUser)
                .doAfterTerminate(() ->
                        log.info("Finalización del request, " +
                                        "tiempo procesando la solicitud en milis: {}",
                                ChronoUnit.MILLIS.between(init, Instant.now())));
    }


    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/user/associate-coin/delete")
    public Mono<Void> deleteCoinFromUser(@RequestBody JsonNode body) {
        var init = Instant.now();
        return Mono.just(body)
                .doFirst(() -> log.info("Iniciando consulta"))
                .doOnNext(jsonSchemaValidator::validateWithJsonSchema)
                .flatMap(apiRestService::deleteCoinFromUser)
                .doAfterTerminate(() ->
                        log.info("Finalización del request, " +
                                        "tiempo procesando la solicitud en milis: {}",
                                ChronoUnit.MILLIS.between(init, Instant.now())));

    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/user/associate-coin/edit")
    public Mono<UserCryptocoinDTO> editAmountCoinFromUser(@RequestBody JsonNode body) {
        var init = Instant.now();
        return Mono.just(body)
                .doFirst(() -> log.info("Iniciando consulta"))
                .doOnNext(jsonSchemaValidator::validateWithJsonSchema)
                .flatMap(apiRestService::editAmounCoinFromUser)
                .doAfterTerminate(() ->
                        log.info("Finalización del request, " +
                                        "tiempo procesando la solicitud en milis: {}",
                                ChronoUnit.MILLIS.between(init, Instant.now())));
    }
}