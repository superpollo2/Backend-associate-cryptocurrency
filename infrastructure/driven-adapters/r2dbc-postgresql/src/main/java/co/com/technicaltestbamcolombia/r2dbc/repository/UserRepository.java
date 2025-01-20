package co.com.technicaltestbamcolombia.r2dbc.repository;

import co.com.technicaltestbamcolombia.model.user.UserDTO;
import co.com.technicaltestbamcolombia.r2dbc.entity.UsersEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UsersEntity, Integer> {
    Mono<UserDTO> findByUsername(String username);
    Mono<UsersEntity> findByUserId(Integer userId);

}
