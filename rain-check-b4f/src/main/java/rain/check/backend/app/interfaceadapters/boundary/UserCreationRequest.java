package rain.check.backend.app.interfaceadapters.boundary;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
public class UserCreationRequest {

    @NotEmpty
    private String userName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String cityName;
}
