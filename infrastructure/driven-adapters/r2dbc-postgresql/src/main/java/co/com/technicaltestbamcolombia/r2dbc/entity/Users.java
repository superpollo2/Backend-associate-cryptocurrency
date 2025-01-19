package co.com.technicaltestbamcolombia.r2dbc.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder(toBuilder = true)
@Table("Users")
public class Users {

    @Id
    private Integer userId;
    private String username;
    private String password;
    private Integer countryId;


}
