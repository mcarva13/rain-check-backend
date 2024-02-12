package rain.check.backend.app.infrastructure.assemblers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import rain.check.backend.app.domain.entities.City;
import rain.check.backend.app.domain.entities.Weather;
import rain.check.backend.app.infrastructure.datamodel.WeatherWS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
class WeatherAssemblerTest {

    @InjectMocks
    private WeatherAssemblerImpl weatherAssembler;

    @Test
    void weatherWSToWeather() {
        // Arrange
        final WeatherWS weatherWS = WeatherWS.builder()
                .hourly(Map.of("time",  List.of(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).minusHours(1).format(DateTimeFormatter.ISO_DATE_TIME))))
                .current(Map.of("time", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).format(DateTimeFormatter.ISO_DATE_TIME)))
                .latitude(null)
                .longitude(null)
                .build();

        final Weather expectedWeather = Weather.builder()
                .hourlyWeather(Map.of("time", List.of(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).minusHours(1).format(DateTimeFormatter.ISO_DATE_TIME))))
                .currentWeather(Map.of("time", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).format(DateTimeFormatter.ISO_DATE_TIME)))
                .build();

        // Act
        final Weather actualWeather = weatherAssembler.weatherWSToWeather(weatherWS);

        // Assert
        assertThat(actualWeather, equalTo(expectedWeather));
    }

    @Test
    void weatherWSToWeatherNull() {
        // Act
        final Weather actualWeather = weatherAssembler.weatherWSToWeather(null);

        // Assert
        assertThat(actualWeather, nullValue(Weather.class));
    }

    @Test
    void weatherWSToWeatherEmptyObject() {
        // Arrange
        final WeatherWS weatherWS = WeatherWS.builder().build();
        final Weather expectedWeather = Weather.builder().build();

        // Act
        final Weather actualWeather = weatherAssembler.weatherWSToWeather(weatherWS);

        // Assert
        assertThat(actualWeather, equalTo(expectedWeather));
    }
}