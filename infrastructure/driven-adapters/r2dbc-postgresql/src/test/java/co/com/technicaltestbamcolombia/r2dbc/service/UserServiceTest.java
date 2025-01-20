package co.com.technicaltestbamcolombia.r2dbc.service;

import co.com.technicaltestbamcolombia.r2dbc.repository.UserRepository;
import co.com.technicaltestbamcolombia.r2dbc.service.builders.TestBuilders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void findByUsername_ShouldReturnUser() {

        String username = "testUser";
        var user = TestBuilders.getUserDTO();

        when(userRepository.findByUsername(username))
                .thenReturn(Mono.just(user));

        var result = userService.findByUsername(username);

        StepVerifier.create(result)
                .expectNext(user)
                .verifyComplete();
        verify(userRepository).findByUsername(username);
    }

    @Test
    void findByUsernameShouldReturnEmptyWhenUserNotFound() {

        String username = "nonexistentUser";
        Mockito.when(userRepository.findByUsername(username))
                .thenReturn(Mono.empty());

        var result = userService.findByUsername(username);

        StepVerifier.create(result)
                .verifyComplete();
        verify(userRepository).findByUsername(username);
    }

    @Test
    void findCountryByUserIdShouldReturnCountryId() {
        Integer userId = 1;
        var userEntity= TestBuilders.getUserEntities();
        when(userRepository.findByUserId(userId))
                .thenReturn(Mono.just(userEntity));

        var result = userService.findCountryByUserId(userId);

        StepVerifier.create(result)
                .expectNext(7)
                .verifyComplete();
        verify(userRepository).findByUserId(userId);
    }

    @Test
    void findCountryByUserId_ShouldReturnEmptyWhenUserNotFound() {
        Integer userId = 999;
        Mockito.when(userRepository.findByUserId(userId))
                .thenReturn(Mono.empty());

        Mono<Integer> result = userService.findCountryByUserId(userId);

        StepVerifier.create(result)
                .verifyComplete();
        verify(userRepository).findByUserId(userId);
    }

}