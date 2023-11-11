package rain.check.backend.app.applicationservices;

import rain.check.backend.app.domain.entities.Weather;

public interface RainCheckService {
    Weather checkWeatherReport(final String latitude, final String longitude);
}
