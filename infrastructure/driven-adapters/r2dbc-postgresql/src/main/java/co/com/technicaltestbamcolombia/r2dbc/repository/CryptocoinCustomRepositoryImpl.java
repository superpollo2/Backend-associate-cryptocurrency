
package co.com.technicaltestbamcolombia.r2dbc.repository;


import co.com.technicaltestbamcolombia.model.Cryptocoin.Cryptocoin;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CryptocoinCustomRepositoryImpl implements CryptocoinCustomRepository {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<Cryptocoin> findCryptocoinsByUserId(Integer userId) {
        var sql = """
            SELECT c.* 
            FROM cryptocoin c
            JOIN usercrytocoin uc ON c.cryptocoin_id = uc.cryptocoin_id
            WHERE uc.user_id = :userId
        """;

        return databaseClient.sql(sql)
                .bind("userId", userId)
                .map((row, metadata) -> new Cryptocoin(
                        row.get("cryptocoin_id", Integer.class),
                        row.get("name", String.class),
                        row.get("symbol", String.class),
                        row.get("exchange_rate", Double.class)
                ))
                .all();
    }

    @Override
    public Flux<Cryptocoin> findCryptocoinByCountryId(Integer countryId) {
        var sql = """
            SELECT c.* 
            FROM cryptocoin c
            JOIN countrycrytocoin uc ON c.cryptocoin_id = uc.cryptocoin_id
            WHERE uc.country_id = :countryId
        """;

        return databaseClient.sql(sql)
                .bind("countryId", countryId)
                .map((row, metadata) -> new Cryptocoin(
                        row.get("cryptocoin_id", Integer.class),
                        row.get("name", String.class),
                        row.get("symbol", String.class),
                        row.get("exchange_rate", Double.class)
                ))
                .all();
    }

}
