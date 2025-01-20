package co.com.technicaltestbamcolombia.config.app;

import co.com.technicaltestbamcolombia.api.service.ApiRestService;
import co.com.technicaltestbamcolombia.model.Cryptocoin.gateways.CryptocoinGateway;
import co.com.technicaltestbamcolombia.model.user.UserDTO;
import co.com.technicaltestbamcolombia.model.user.gateways.UserGateway;
import co.com.technicaltestbamcolombia.r2dbc.mapper.MapperEntity;
import co.com.technicaltestbamcolombia.r2dbc.repository.CryptocoinCustomRepository;
import co.com.technicaltestbamcolombia.r2dbc.repository.CryptocoinCustomRepositoryImpl;
import co.com.technicaltestbamcolombia.r2dbc.repository.UserCryptocoinRepository;
import co.com.technicaltestbamcolombia.r2dbc.repository.UserRepository;
import co.com.technicaltestbamcolombia.r2dbc.service.CryptocoinService;
import co.com.technicaltestbamcolombia.r2dbc.service.UserService;
import co.com.technicaltestbamcolombia.usecase.CryptocoinUseCase;
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
        public CryptocoinUseCase cryptocoinUseCase(CryptocoinGateway cryptocoinGateway) {
                return new CryptocoinUseCase(cryptocoinGateway);

        }

        @Bean
        public UserService userService(UserRepository userRepository) {
                return new UserService(userRepository);
        }
        @Bean
        public UserGateway userGateway(UserRepository userRepository) {
                return new UserService(userRepository);
        }
        @Bean
        public CryptocoinGateway cryptocoinGateway(CryptocoinCustomRepository cryptocoinCustomRepository,
                                                   UserCryptocoinRepository userCryptocoinRepository,
                                                   MapperEntity mapperEntity,
                                                   UserService userService) {
                return new CryptocoinService(cryptocoinCustomRepository, userCryptocoinRepository, mapperEntity
                ,userService);
        }



}
