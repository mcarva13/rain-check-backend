package rain.check.backend.app.infrastructure;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import rain.check.backend.app.infrastructure.datamodel.WeatherWS;

import java.util.List;

/**
 * External weather service API.
 */
@RegisterRestClient(configKey = "weather-service-api")
public interface ExternalWeatherService {

    /**
     * Timezone default value.
     */
    String TIMEZONE_DEFAULT = "auto";
    /**
     * Default number of forecast days.
     */
    String FORECAST_DAYS_DEFAULT = "3";

    /**
     * Calls the external weather service to fetch the weather information for a specific location.
     *
     * @param latitude       - location latitude
     * @param longitude      - location longitude
     * @param currentWeather - List of weather variables for the current weather info
     * @param hourlyWeather  - List of weather variables for the hourly weather forecast
     * @param timezone       - Client timezone
     * @param forecastDays   - Number of forecast days
     * @return {@link Response} that can be mapped to a {@link WeatherWS} object.
     */
    @GET
    @APIResponse(
            responseCode = "200",
            description = "Get weather attribute for coordinates",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = WeatherWS.class)
            )
    )
    Response getWeatherForCoordinates(
            @QueryParam("latitude") String latitude,
            @QueryParam("longitude") String longitude,
            @QueryParam("current") List<String> currentWeather,
            @QueryParam("hourly") List<String> hourlyWeather,
            @DefaultValue(TIMEZONE_DEFAULT) @QueryParam("timezone") String timezone,
            @DefaultValue(FORECAST_DAYS_DEFAULT) @QueryParam("forecast_days") String forecastDays);
}
