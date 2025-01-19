package co.com.technicaltestbamcolombia.r2dbc.repository;

import co.com.technicaltestbamcolombia.model.Cryptocoin.Cryptocoin;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CryptocoinRepository extends ReactiveCrudRepository<Cryptocoin, Integer> {
    Mono<Cryptocoin> findAllByCryptocoinId(Integer cryptocoinId);

}
