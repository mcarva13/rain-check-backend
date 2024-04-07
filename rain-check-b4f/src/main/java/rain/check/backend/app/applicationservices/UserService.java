package rain.check.backend.app.applicationservices;

import rain.check.backend.app.domain.entities.User;
import rain.check.backend.app.interfaceadapters.boundary.UserCreationRequest;

/**
 * User service.
 */
public interface UserService {

    User createNewUser(final UserCreationRequest user);
}
