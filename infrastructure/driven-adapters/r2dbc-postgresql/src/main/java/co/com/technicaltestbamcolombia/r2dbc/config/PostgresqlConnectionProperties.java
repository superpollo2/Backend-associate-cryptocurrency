package co.com.technicaltestbamcolombia.r2dbc.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostgresqlConnectionProperties{

        private String dbname;
        private Integer schema;
        private String username;
        private String password;
        private String host;
        private Integer port;
}
