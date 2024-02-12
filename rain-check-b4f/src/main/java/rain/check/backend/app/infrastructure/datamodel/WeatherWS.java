package rain.check.backend.app.infrastructure.datamodel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Weather web service entity.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WeatherWS {

    private String longitude;
    private String latitude;
    private Map<String, List<String>> hourly;
    private Map<String, String> current;
}
