package co.com.technicaltestbamcolombia.r2dbc.service;

import co.com.technicaltestbamcolombia.model.user.UserDTO;
import co.com.technicaltestbamcolombia.model.user.gateways.UserGateway;
import co.com.technicaltestbamcolombia.r2dbc.entity.UsersEntity;
import co.com.technicaltestbamcolombia.r2dbc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserService implements UserGateway {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDTO> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Mono<Integer> findCountryByUserId(Integer userId) {
        return  userRepository.findByUserId(userId)
                .map(UsersEntity::getCountryId);
    }
}
