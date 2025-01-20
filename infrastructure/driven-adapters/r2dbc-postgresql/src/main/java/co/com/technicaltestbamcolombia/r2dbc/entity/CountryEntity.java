package co.com.technicaltestbamcolombia.r2dbc.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder(toBuilder = true)
@Table("country")
public class CountryEntity {

    @Id
    private Integer countryId;
    private String name;

}
