package co.com.technicaltestbamcolombia.r2dbc.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(schema="cryptoManagementDB", name="users")
public class User {

    @Id
    private String username;
    private String password;
    private UUID id;
    private Integer country;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

}
