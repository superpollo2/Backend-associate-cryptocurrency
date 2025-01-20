package co.com.technicaltestbamcolombia.model.Cryptocoin;


import co.com.technicaltestbamcolombia.model.country.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CrytocoinCountryDTO {

    private CountryDTO countryDTO;
    private List<CryptocoinDTO> cryptocoins;
}
