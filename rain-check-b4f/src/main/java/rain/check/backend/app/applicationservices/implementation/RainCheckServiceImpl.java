package rain.check.backend.app.applicationservices.implementation;

import rain.check.backend.app.applicationservices.RainCheckService;
import rain.check.backend.app.infrastructure.assemblers.WeatherAssembler;
import rain.check.backend.app.domain.entities.Weather;
import rain.check.backend.app.domain.entities.WeatherVariable;
import rain.check.backend.app.infrastructure.WeatherService;
import rain.check.backend.app.infrastructure.datamodel.WeatherWS;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Dependent
public class RainCheckServiceImpl implements RainCheckService {

    @RestClient
    private WeatherService weatherService;
    @Inject
    private WeatherAssembler weatherAssembler;

    @Override
    public Weather checkWeatherReport(final String latitude, final String longitude) {
        final List<String> weatherVariables = getWeatherVariables();

        final WeatherWS weatherWS = weatherService
                .getWeatherForCoordinates(
                        latitude,
                        longitude,
                        weatherVariables,
                        weatherVariables,
                        WeatherService.TIMEZONE_DEFAULT,
                        WeatherService.FORECAST_DAYS_DEFAULT)
                .readEntity(WeatherWS.class);

        return weatherAssembler.weatherWSToWeather(weatherWS);
    }

    private static List<String> getWeatherVariables() {
        return List.of(
                WeatherVariable.PRECIPITATION.getWeatherVar(),
                WeatherVariable.RAIN.getWeatherVar(),
                WeatherVariable.SHOWERS.getWeatherVar(),
                WeatherVariable.TEMPERATURE.getWeatherVar());
    }


}
