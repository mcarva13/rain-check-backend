package rain.check.backend.app.interfaceadapters.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/raincheck")
@RegisterRestClient
@RequestScoped
public interface RainCheckController {

    @GET
    @Path("/city")
    Response generateWeatherReport(@QueryParam("lat") String latitude,
                                   @QueryParam("long") String longitude);

}
