package rain.check.backend.app.applicationservices.implementation;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rain.check.backend.app.domain.entities.Weather;
import rain.check.backend.app.infrastructure.ExternalWeatherService;
import rain.check.backend.app.infrastructure.assemblers.CityAssembler;
import rain.check.backend.app.infrastructure.assemblers.WeatherAssembler;
import rain.check.backend.app.infrastructure.datamodel.WeatherWS;
import rain.check.backend.database.applicationservices.UserRepository;
import rain.check.backend.database.domain.entities.CityJPA;
import rain.check.backend.database.domain.entities.UserJPA;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExternalWeatherServiceImplTest {

    @Mock
    ExternalWeatherService externalWeatherService;

    @Mock
    WeatherAssembler weatherAssembler;

    @Mock
    UserRepository userRepository;

    @Mock
    CityAssembler cityAssembler;

    @InjectMocks
    WeatherServiceImpl weatherService;

    private final static UUID USER_UUID = UUID.randomUUID();

    @Test
    void checkWeatherReport() {
        // Arrange
        final UserJPA mockUser = Mockito.mock(UserJPA.class);
        final CityJPA mockCity = Mockito.mock(CityJPA.class);
        final Weather mockWeather = Mockito.mock(Weather.class);
        final Response mockResponse = Mockito.mock(Response.class);
        final WeatherWS weatherWS = WeatherWS.builder()
                .build();

        when(userRepository.getUserFromDB(USER_UUID.toString())).thenReturn(mockUser);
        when(userRepository.getUserCityFromDB(mockUser)).thenReturn(mockCity);
        when(mockCity.getLatitude()).thenReturn(BigDecimal.ONE);
        when(mockCity.getLongitude()).thenReturn(BigDecimal.ONE);

        when(externalWeatherService.getWeatherForCoordinates(any(),any(),any(),any(),any(),any()))
                .thenReturn(mockResponse);
        when(mockResponse.readEntity(WeatherWS.class)).thenReturn(weatherWS);

        when(weatherAssembler.weatherWSToWeather(weatherWS)).thenReturn(mockWeather);

        // Act
        final Weather result = weatherService.checkWeatherReport(USER_UUID.toString());

        // Assert
        assertEquals(mockWeather, result);
    }
}