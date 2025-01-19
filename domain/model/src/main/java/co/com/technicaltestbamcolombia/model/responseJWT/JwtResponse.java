package co.com.technicaltestbamcolombia.model.responseJWT;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class JwtResponse {

    private String token;

}
