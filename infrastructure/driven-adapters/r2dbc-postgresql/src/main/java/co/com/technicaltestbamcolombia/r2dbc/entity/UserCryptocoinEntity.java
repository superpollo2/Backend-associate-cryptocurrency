package co.com.technicaltestbamcolombia.r2dbc.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@Table("UserCryptocoin")
public class UserCryptocoinEntity {

    @Id
    private Long id;
    private Integer userId;
    private Integer cryptocoinId;
}
