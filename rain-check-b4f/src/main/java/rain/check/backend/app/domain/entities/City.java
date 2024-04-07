package rain.check.backend.app.domain.entities;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * City entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    @NotEmpty
    private String cityName;
    @NotEmpty
    private BigDecimal longitude;
    @NotEmpty
    private BigDecimal latitude;
    @NotEmpty
    private String country;
    @NotEmpty
    private String timezone;
}
