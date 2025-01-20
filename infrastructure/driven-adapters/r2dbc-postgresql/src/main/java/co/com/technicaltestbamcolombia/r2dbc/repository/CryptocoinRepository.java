package co.com.technicaltestbamcolombia.r2dbc.repository;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CryptocoinRepository extends ReactiveCrudRepository<CryptocoinDTO, Integer> {
    Mono<CryptocoinDTO> findAllByCryptocoinId(Integer cryptocoinId);

}
