package rain.check.backend.app.applicationservices.implementation;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import rain.check.backend.app.applicationservices.WeatherService;
import rain.check.backend.app.domain.entities.City;
import rain.check.backend.app.domain.entities.Weather;
import rain.check.backend.app.domain.entities.WeatherVariable;
import rain.check.backend.app.infrastructure.ExternalWeatherService;
import rain.check.backend.app.infrastructure.assemblers.CityAssembler;
import rain.check.backend.app.infrastructure.assemblers.WeatherAssembler;
import rain.check.backend.app.infrastructure.datamodel.WeatherWS;
import rain.check.backend.database.applicationservices.UserRepository;
import rain.check.backend.database.domain.entities.CityJPA;
import rain.check.backend.database.domain.entities.UserJPA;

import java.util.List;

/**
 * Weather service implementation.
 */
@Dependent
public class WeatherServiceImpl implements WeatherService {

    @RestClient
    private ExternalWeatherService externalWeatherService;
    @Inject
    WeatherAssembler weatherAssembler;
    @Inject
    CityAssembler cityAssembler;
    @Inject
    UserRepository userRepository;

    @Override
    public Weather checkWeatherReport(final String userId) {
        // 1 - Get User Data
        final UserJPA user = userRepository.getUserFromDB(userId);
        final CityJPA userCity = userRepository.getUserCityFromDB(user);

        // 2 - Create Weather Variables
        final List<String> weatherVariables = getWeatherVariables();

        // 3 - Fetch from external Service
        final WeatherWS weatherWS = externalWeatherService
                .getWeatherForCoordinates(
                        userCity.getLatitude().toString(),
                        userCity.getLongitude().toString(),
                        weatherVariables,
                        weatherVariables,
                        ExternalWeatherService.TIMEZONE_DEFAULT,
                        ExternalWeatherService.FORECAST_DAYS_DEFAULT)
                .readEntity(WeatherWS.class);

        final Weather weatherReport = weatherAssembler.weatherWSToWeather(weatherWS);
        enrichWeatherReportWithUserCityInfo(weatherReport, userCity);

        return weatherReport;
    }

    private List<String> getWeatherVariables() {
        return List.of(
                WeatherVariable.PRECIPITATION.getWeatherVar(),
                WeatherVariable.RAIN.getWeatherVar(),
                WeatherVariable.SHOWERS.getWeatherVar(),
                WeatherVariable.TEMPERATURE.getWeatherVar());
    }

    private void enrichWeatherReportWithUserCityInfo(final Weather weatherReport,
                                                     final CityJPA userCity) {
        weatherReport.setCity(cityAssembler.cityJPAToCity(userCity));
    }
}
