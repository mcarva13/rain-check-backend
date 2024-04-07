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

/**
 * External geolocation service API.
 */
@RegisterRestClient(configKey = "geolocation-service-api")
public interface ExternalGeolocationService {

    /**
     * Default number of cities returned.
     */
    String NUMBER_OF_CITY_COUNT = "1";

    /**
     * Calls the external geolocation service to fetch the city information for a specific location.
     *
     * @return {@link Response} that can be mapped to a object //TODO.
     */
    @GET
    @APIResponse(
            responseCode = "200",
            description = "Get city info by name",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ExternalWeatherService.class)
            )
    )
    Response getCityGeolocationInfo(
            @QueryParam("name") String name,
            @DefaultValue(NUMBER_OF_CITY_COUNT) @QueryParam("count") String count);
}
