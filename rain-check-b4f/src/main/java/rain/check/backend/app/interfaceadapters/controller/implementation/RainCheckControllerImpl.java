package rain.check.backend.app.interfaceadapters.controller.implementation;

import rain.check.backend.app.applicationservices.RainCheckService;
import rain.check.backend.app.interfaceadapters.controller.RainCheckController;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Dependent
public class RainCheckControllerImpl implements RainCheckController {

    @Inject
    private RainCheckService rainCheckService;

    @Override
    @APIResponse(
            responseCode = "200",
            description = "Get weather from city with given latitude and longitude coordinates",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = String.class)
            )
    )
    public Response generateWeatherReport(final String latitude, final String longitude) {
        return Response.ok(rainCheckService.checkWeatherReport(latitude,longitude)).build();
    }
}
