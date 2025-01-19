package co.com.technicaltestbamcolombia.r2dbc.repository;

import co.com.technicaltestbamcolombia.model.Cryptocoin.Cryptocoin;
import reactor.core.publisher.Flux;

public interface CryptocoinCustomRepository {
    Flux<Cryptocoin> findCryptocoinsByUserId(Integer userId);
    Flux<Cryptocoin> findCryptocoinByCountryId(Integer countryId);
}
