package rain.check.backend.app.domain.entities;

import lombok.Getter;

/**
 * Enum with the possible weather variables to call the external weather service.
 */
@Getter
public enum WeatherVariable {

    /**
     * Precipitation variable.
     */
    PRECIPITATION("precipitation"),
    /**
     * Rain variable.
     */
    RAIN("rain"),
    /**
     * Showers variable.
     */
    SHOWERS("showers"),
    /**
     * Temperature variable.
     */
    TEMPERATURE("temperature_2m");

    private final String weatherVar;

    WeatherVariable(final String weatherVar) {
        this.weatherVar = weatherVar;
    }
}
