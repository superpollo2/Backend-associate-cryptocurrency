package co.com.technicaltestbamcolombia.r2dbc.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(schema="cryptoManagementDB", name="users")
public class CryptocoinEntity {
    @Id
    private Integer cryptocoinId;
    private String name;
    private String symbol;
    private Double exchangeRate;

}
