package rain.check.backend.app.domain.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * User entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @NotEmpty
    private String userName;
    @Email
    private String email;
    private City city;
}
