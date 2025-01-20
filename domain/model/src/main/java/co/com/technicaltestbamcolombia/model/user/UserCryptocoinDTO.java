package co.com.technicaltestbamcolombia.model.user;


import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserCryptocoinDTO {
    private Integer userId;
    private Integer cryptocoinId;
    private Integer amount;
}
