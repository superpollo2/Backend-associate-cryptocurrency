package co.com.technicaltestbamcolombia.r2dbc.repository;

import co.com.technicaltestbamcolombia.model.user.UserDTO;
import co.com.technicaltestbamcolombia.r2dbc.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepositoryPostgrestSQL extends ReactiveCrudRepository<User, String> {
    Mono<UserDTO> findByUsername(String username);
}
