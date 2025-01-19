package co.com.technicaltestbamcolombia.r2dbc.service;

import co.com.technicaltestbamcolombia.model.user.UserDTO;
import co.com.technicaltestbamcolombia.model.user.gateways.UserGateway;
import co.com.technicaltestbamcolombia.r2dbc.repository.UserRepositoryPostgrestSQL;
import reactor.core.publisher.Mono;

public class UserService implements UserGateway {

    private UserRepositoryPostgrestSQL userRepository;
    @Override
    public Mono<UserDTO> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
