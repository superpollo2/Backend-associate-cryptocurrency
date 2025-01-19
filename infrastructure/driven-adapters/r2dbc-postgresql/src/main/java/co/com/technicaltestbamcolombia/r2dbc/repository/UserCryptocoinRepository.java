package co.com.technicaltestbamcolombia.r2dbc.repository;

import co.com.technicaltestbamcolombia.r2dbc.entity.UserCryptocoinEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserCryptocoinRepository extends ReactiveCrudRepository<UserCryptocoinEntity, Integer> {

}
