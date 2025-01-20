
package co.com.technicaltestbamcolombia.r2dbc.repository;


import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinUserAmountDTO;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CrytocoinCountryDTO;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CryptocoinCustomRepositoryImpl implements CryptocoinCustomRepository {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<CryptocoinUserAmountDTO> findCryptocoinsByUserId(Integer userId) {
        var sql = """
            SELECT c.cryptocoin_id, c."name", c.symbol, c.exchange_rate , uc.amount
            FROM cryptocoin c
            JOIN usercrytocoin uc ON c.cryptocoin_id = uc.cryptocoin_id
            WHERE uc.user_id = :userId
        """;

        return databaseClient.sql(sql)
                .bind("userId", userId)
                .map((row, metadata) -> new CryptocoinUserAmountDTO(
                        row.get("cryptocoin_id", Integer.class),
                        row.get("name", String.class),
                        row.get("symbol", String.class),
                        row.get("exchange_rate", Double.class),
                        row.get("amount", Integer.class)
                ))
                .all();
    }

    @Override
    public Mono<CryptocoinUserAmountDTO> findOneCryptocoinsByUserId(Integer userId, Integer cryptocoinId) {
        var sql = """
            SELECT c.cryptocoin_id, c."name", c.symbol, c.exchange_rate , uc.amount
            FROM cryptocoin c
            JOIN usercrytocoin uc ON c.cryptocoin_id = uc.cryptocoin_id
            WHERE uc.user_id = :userId AND uc.cryptocoin_id = :cryptocoinId
        """;

        return databaseClient.sql(sql)
                .bind("userId", userId)
                .bind("cryptocoinId", cryptocoinId)
                .map((row, metadata) -> new CryptocoinUserAmountDTO(
                        row.get("cryptocoin_id", Integer.class),
                        row.get("name", String.class),
                        row.get("symbol", String.class),
                        row.get("exchange_rate", Double.class),
                        row.get("amount", Integer.class)
                ))
                .all().single();
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
