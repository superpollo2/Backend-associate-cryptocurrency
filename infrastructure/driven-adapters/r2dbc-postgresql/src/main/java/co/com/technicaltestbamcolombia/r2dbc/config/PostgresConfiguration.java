package co.com.technicaltestbamcolombia.r2dbc.config;

import co.com.technicaltestbamcolombia.r2dbc.repository.CryptocoinCustomRepository;
import co.com.technicaltestbamcolombia.r2dbc.repository.CryptocoinCustomRepositoryImpl;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@EnableConfigurationProperties(R2dbcProperties.class)
@EnableR2dbcRepositories(basePackages = "co.com.technicaltestbamcolombia.r2dbc.repository")
@Configuration
public class PostgresConfiguration extends AbstractR2dbcConfiguration {

	private final PostgresqlConnectionProperties properties;
	private final R2dbcProperties r2dbcProperties;


	@Bean
	public ConnectionFactory connectionFactory() {
		var configuration = PostgresqlConnectionConfiguration.builder()
				.host(properties.getHost())
				.database(properties.getDbname())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.port(properties.getPort());
		return new PostgresqlConnectionFactory(configuration.build());
	}

	@Bean(destroyMethod = "dispose")
	public ConnectionPool connectionPool() {
		var pool = r2dbcProperties.getPool();
		var builder = ConnectionPoolConfiguration.builder(connectionFactory())
				.maxSize(pool.getMaxSize())
				.initialSize(pool.getInitialSize())
				.maxIdleTime(pool.getMaxIdleTime());

		if(StringUtils.hasText(pool.getValidationQuery())){
			builder.validationQuery(pool.getValidationQuery());
		}
		return new ConnectionPool(builder.build());
	}

	@Bean
	public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
		return DatabaseClient.create(connectionFactory);
	}

	@Bean
	public CryptocoinCustomRepository cryptocoinCustomRepository(DatabaseClient databaseClient) {
		return new CryptocoinCustomRepositoryImpl(databaseClient);
	}
}