package rain.check.backend.app.applicationservices;

import rain.check.backend.app.domain.entities.Weather;

/**
 * Rain Check service.
 */
public interface RainCheckService {

    /**
     * Check the weather report for a given user and its associated location.
     *
     * @param userId - user UUID.
     * @return {@link Weather} object.
     */
    Weather checkWeatherReport(final String userId);
}
