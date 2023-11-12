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

import java.util.List;

@RegisterRestClient(configKey = "weather-service-api")
public interface WeatherService {

    String TIMEZONE_DEFAULT = "auto";
    String FORECAST_DAYS_DEFAULT = "3";

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Get weather attribute for coordinates",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = WeatherService.class)
            )
    )
    Response getWeatherForCoordinates(
            @QueryParam("latitude") String latitude,
            @QueryParam("longitude") String longitude,
            @QueryParam("current") List<String> currentWeather,
            @QueryParam("hourly") List<String> variable,
            @DefaultValue(TIMEZONE_DEFAULT) @QueryParam("timezone") String timezone,
            @DefaultValue(FORECAST_DAYS_DEFAULT) @QueryParam("forecast_days") String forecastDays);
}
