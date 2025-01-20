package co.com.technicaltestbamcolombia.model.user;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {

    private int userId;
    private String username;
    private String password;
    private int countryId;
}