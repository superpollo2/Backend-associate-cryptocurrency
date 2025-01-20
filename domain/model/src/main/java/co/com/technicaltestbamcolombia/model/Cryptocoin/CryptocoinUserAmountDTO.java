package co.com.technicaltestbamcolombia.model.Cryptocoin;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CryptocoinUserAmountDTO {

    private Integer cryptocoinId;
    private String cryptocoinName;
    private String symbol;
    private Double exchangeRate;
    private Integer amount;
}
