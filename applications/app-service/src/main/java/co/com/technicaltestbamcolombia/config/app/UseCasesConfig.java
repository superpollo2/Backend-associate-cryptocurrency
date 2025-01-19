package co.com.technicaltestbamcolombia.config.app;

import co.com.technicaltestbamcolombia.model.user.UserDTO;
import co.com.technicaltestbamcolombia.model.user.gateways.UserGateway;
import co.com.technicaltestbamcolombia.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import reactor.core.publisher.Mono;

@Configuration
@ComponentScan(basePackages = "co.com.technicaltestbamcolombia.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {


        @Bean
        public UserUseCase userUseCase(UserGateway userGateway) {
                return new UserUseCase(userGateway);

        }


}
