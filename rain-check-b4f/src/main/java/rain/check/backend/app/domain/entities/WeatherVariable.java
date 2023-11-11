package rain.check.backend.app.domain.entities;

import lombok.Getter;

@Getter
public enum WeatherVariable {
    PRECIPITATION("precipitation"),
    RAIN("rain"),
    SHOWERS("showers"),
    TEMPERATURE("temperature_2m");

    private final String weatherVar;

    WeatherVariable(final String weatherVar) {
        this.weatherVar = weatherVar;
    }
}
