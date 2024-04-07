package rain.check.backend.app.interfaceadapters.controller.implementation;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import rain.check.backend.app.applicationservices.WeatherService;
import rain.check.backend.app.interfaceadapters.controller.WeatherController;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

/**
 * Weather controller implementation.
 */
@Dependent
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WeatherControllerImpl implements WeatherController {

    @Inject
    private WeatherService weatherService;

    @Override
    @APIResponse(
            responseCode = "200",
            description = "Get weather for a specific user",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = String.class)
            )
    )
    public Response generateWeatherReport(final String userId) {
        return Response.ok(weatherService.checkWeatherReport(userId)).build();
    }
}
