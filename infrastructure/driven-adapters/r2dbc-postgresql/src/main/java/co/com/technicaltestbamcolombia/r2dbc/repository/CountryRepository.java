package co.com.technicaltestbamcolombia.r2dbc.repository;

import co.com.technicaltestbamcolombia.r2dbc.entity.CountryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CountryRepository extends ReactiveCrudRepository<CountryEntity, Integer> {
    Mono<CountryEntity> getCountryEntityByCountryId(Integer countryId);
}
