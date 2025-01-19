package co.com.technicaltestbamcolombia.r2dbc.entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(schema="cryptoManagementDB", name="users")
public class Cryptocoin {
    @Id
    private Integer cryptocoinId;
    private String name;
    private String symbol;
    private Double exchangeRate;

}
