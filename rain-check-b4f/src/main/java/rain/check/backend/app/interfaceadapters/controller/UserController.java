package rain.check.backend.app.interfaceadapters.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import rain.check.backend.app.interfaceadapters.boundary.UserCreationRequest;

/**
 * User service controller.
 */
@Path("/user")
@RegisterRestClient
@RequestScoped
public interface UserController {

    @POST
    Response createNewUser(@NotNull @Valid final UserCreationRequest user);
}
