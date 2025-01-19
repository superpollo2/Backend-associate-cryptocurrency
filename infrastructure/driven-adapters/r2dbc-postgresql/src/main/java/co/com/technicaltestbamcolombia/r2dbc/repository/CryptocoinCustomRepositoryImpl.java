
package co.com.technicaltestbamcolombia.r2dbc.repository;


import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
import co.com.technicaltestbamcolombia.model.config.CryptoErrorCode;
import co.com.technicaltestbamcolombia.model.config.CryptoException;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CryptocoinCustomRepositoryImpl implements CryptocoinCustomRepository {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<CryptocoinDTO> findCryptocoinsByUserId(Integer userId) {
        var sql = """
            SELECT c.* 
            FROM cryptocoin c
            JOIN usercrytocoin uc ON c.cryptocoin_id = uc.cryptocoin_id
            WHERE uc.user_id = :userId
        """;

        return databaseClient.sql(sql)
                .bind("userId", userId)
                .map((row, metadata) -> new CryptocoinDTO(
                        row.get("cryptocoin_id", Integer.class),
                        row.get("name", String.class),
                        row.get("symbol", String.class),
                        row.get("exchange_rate", Double.class)
                ))
                .all();
    }

    @Override
    public Flux<CryptocoinDTO> findCryptocoinByCountryId(Integer countryId) {
        var sql = """
            SELECT c.* 
            FROM cryptocoin c
            JOIN countrycrytocoin uc ON c.cryptocoin_id = uc.cryptocoin_id
            WHERE uc.country_id = :countryId
        """;

        return databaseClient.sql(sql)
                .bind("countryId", countryId)
                .map((row, metadata) -> new CryptocoinDTO(
                        row.get("cryptocoin_id", Integer.class),
                        row.get("name", String.class),
                        row.get("symbol", String.class),
                        row.get("exchange_rate", Double.class)
                ))
                .all();
    }

    @Override
    public Mono<Long> updateAmountCointUser(UserCryptocoinDTO userCryptocoinDTO) {
        var sql = """
            UPDATE usercrytocoin 
            SET amount = amount + :amount
            WHERE cryptocoin_id = :cryptocoinId AND user_id = :userId
            AND (amount + :amount) >= 0
        """;

        return databaseClient.sql(sql)
                .bind("amount", userCryptocoinDTO.getAmount())
                .bind("cryptocoinId", userCryptocoinDTO.getCryptocoinId())
                .bind("userId", userCryptocoinDTO.getUserId())
                .fetch()
                .rowsUpdated();

    }

}
