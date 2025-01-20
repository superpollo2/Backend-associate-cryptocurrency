package co.com.technicaltestbamcolombia.config.secrets;

import co.com.technicaltestbamcolombia.r2dbc.config.PostgresqlConnectionProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local")
@Configuration
public class SecretConfigLocal {

    @Value("${db.name}")
    private String name;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String pass;
    @Value("${db.port}")
    private String port;
    @Value("${db.host}")
    private String host;

    @Bean
    public PostgresqlConnectionProperties getSecret() {
        return PostgresqlConnectionProperties.builder()
                .password(pass)
                .dbname(name)
                .host(host)
                .port(Integer.parseInt(port))
                .username(username)
                .build();
    }

}