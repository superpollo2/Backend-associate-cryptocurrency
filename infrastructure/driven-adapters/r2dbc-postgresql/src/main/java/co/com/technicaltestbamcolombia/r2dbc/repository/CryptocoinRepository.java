package co.com.technicaltestbamcolombia.r2dbc.repository;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinUserAmountDTO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CryptocoinRepository extends ReactiveCrudRepository<CryptocoinUserAmountDTO, Integer> {
    Mono<CryptocoinUserAmountDTO> findAllByCryptocoinId(Integer cryptocoinId);

}
