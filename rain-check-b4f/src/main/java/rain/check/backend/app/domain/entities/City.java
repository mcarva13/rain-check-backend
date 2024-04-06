package rain.check.backend.app.domain.entities;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * City entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    private String cityName;
    @NotEmpty
    private String longitude;
    @NotEmpty
    private String latitude;
}
