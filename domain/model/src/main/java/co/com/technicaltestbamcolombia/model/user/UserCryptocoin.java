package co.com.technicaltestbamcolombia.model.user;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserCryptocoin {
    private Integer userId;
    private Integer cryptocoinId;
}
