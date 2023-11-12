package rain.check.backend.app.applicationservices.implementation;

import jakarta.ws.rs.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import rain.check.backend.app.applicationservices.RainCheckService;
import rain.check.backend.app.infrastructure.assemblers.WeatherAssembler;
import rain.check.backend.app.domain.entities.Weather;
import rain.check.backend.app.domain.entities.WeatherVariable;
import rain.check.backend.app.infrastructure.WeatherService;
import rain.check.backend.app.infrastructure.datamodel.WeatherWS;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import rain.check.backend.database.entity.CityJPA;
import rain.check.backend.database.entity.UserJPA;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Dependent
public class RainCheckServiceImpl implements RainCheckService {

    @RestClient
    private WeatherService weatherService;
    @Inject
    private WeatherAssembler weatherAssembler;

    @Override
    public Weather checkWeatherReport(final String userId) {
        // 1 - Get User Data
        final UserJPA user = getUserOrThrowException(userId);
        final CityJPA userCity = getUserCityOrThrowException(user);

        // 2 - Create Weather Variables
        final List<String> weatherVariables = getWeatherVariables();

        // 3 - Fetch from external Service
        final WeatherWS weatherWS = weatherService
                .getWeatherForCoordinates(
                        userCity.getLatitude().toString(),
                        userCity.getLongitude().toString(),
                        weatherVariables,
                        weatherVariables,
                        WeatherService.TIMEZONE_DEFAULT,
                        WeatherService.FORECAST_DAYS_DEFAULT)
                .readEntity(WeatherWS.class);

        return weatherAssembler.weatherWSToWeather(weatherWS);
    }

    private CityJPA getUserCityOrThrowException(UserJPA user) {
        final CityJPA userCity = user.getCity();
        return (CityJPA) returnObjectOrThrowNotFoundException(userCity, "User doesn't have an associated city");
    }

    private UserJPA getUserOrThrowException(String userId) {
        final UserJPA userJPA = UserJPA.findById(UUID.fromString(userId));
        return (UserJPA) returnObjectOrThrowNotFoundException(userJPA, "User not found in the database");
    }

    private <T> Object returnObjectOrThrowNotFoundException(final T obj,
                                                            final String message) {
        if (Objects.isNull(obj))
            throw new NotFoundException(message);
        return obj;
    }

    private static List<String> getWeatherVariables() {
        return List.of(
                WeatherVariable.PRECIPITATION.getWeatherVar(),
                WeatherVariable.RAIN.getWeatherVar(),
                WeatherVariable.SHOWERS.getWeatherVar(),
                WeatherVariable.TEMPERATURE.getWeatherVar());
    }
}
