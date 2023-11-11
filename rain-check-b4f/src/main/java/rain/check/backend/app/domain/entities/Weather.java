package rain.check.backend.app.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Weather {

    private City city;
    private Map<String, List<String>> hourlyPrecipitation;
    private Map<String, String> currentPrecipitation;
}
