package co.com.technicaltestbamcolombia.r2dbc.repository;

import co.com.technicaltestbamcolombia.r2dbc.entity.UserCryptocoinEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserCryptocoinRepository extends ReactiveCrudRepository<UserCryptocoinEntity, Integer> {

    Mono<Void> deleteUserCryptocoinEntityByCryptocoinIdAndUserId(Integer cryptocoinId, Integer userId);
    Mono<UserCryptocoinEntity> findByCryptocoinIdAndUserId(Integer cryptocoinId, Integer userId);



}
