package rain.check.backend.app.interfaceadapters.controller.implementation;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rain.check.backend.app.applicationservices.UserService;
import rain.check.backend.app.interfaceadapters.boundary.UserCreationRequest;
import rain.check.backend.app.interfaceadapters.controller.UserController;

/**
 * User controller implementation.
 */
@Dependent
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserControllerImpl implements UserController {

    @Inject
    private UserService userService;

    @Override
    public Response createNewUser(final UserCreationRequest user) {
        return Response.ok(userService.createNewUser(user)).build();
    }
}
