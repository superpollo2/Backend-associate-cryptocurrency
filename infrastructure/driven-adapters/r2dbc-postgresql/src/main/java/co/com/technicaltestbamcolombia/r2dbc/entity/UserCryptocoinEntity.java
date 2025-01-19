package co.com.technicaltestbamcolombia.r2dbc.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table("usercrytocoin")
public class UserCryptocoinEntity {

    @Id
    private Integer userId;
    @Id
    private Integer cryptocoinId;
    private Integer amount;
}
