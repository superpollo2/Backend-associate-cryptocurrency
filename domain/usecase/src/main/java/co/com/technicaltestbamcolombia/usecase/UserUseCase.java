package co.com.technicaltestbamcolombia.usecase;

import co.com.technicaltestbamcolombia.model.user.UserDTO;
import co.com.technicaltestbamcolombia.model.user.gateways.UserGateway;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class UserUseCase {

    private final UserGateway userGateway;

    public Mono<UserDTO> find(String username) {
        return userGateway.findByUsername(username);
    }
}
