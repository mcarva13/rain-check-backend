package rain.check.backend.app.interfaceadapters.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * Rain Check service controller.
 */
@Path("/raincheck")
@RegisterRestClient
@RequestScoped
public interface RainCheckController {

    /**
     * Generate a weather report for a given user on the database.
     *
     * @param userId - user UUID.
     * @return {@link Response} with the user Weather report
     */
    @GET
    @Path("/user")
    Response generateWeatherReport(@QueryParam("id") String userId);

}
