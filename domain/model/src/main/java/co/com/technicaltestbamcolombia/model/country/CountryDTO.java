package co.com.technicaltestbamcolombia.model.country;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class CountryDTO {

    private String countryName;
    private Integer countryId;
}
