package co.com.technicaltestbamcolombia.model.Cryptocoin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CryptocoinDTO {
    private Integer cryptocoinId;
    private String cryptocoinName;
    private String symbol;
    private Double exchangeRate;
}
