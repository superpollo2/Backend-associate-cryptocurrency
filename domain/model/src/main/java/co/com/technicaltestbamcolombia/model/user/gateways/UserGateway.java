package co.com.technicaltestbamcolombia.model.user.gateways;

import co.com.technicaltestbamcolombia.model.user.UserDTO;
import reactor.core.publisher.Mono;

public interface UserGateway {
    Mono<UserDTO> findByUsername(String username);
    Mono<Integer> findCountryByUserId(Integer userId);
}
