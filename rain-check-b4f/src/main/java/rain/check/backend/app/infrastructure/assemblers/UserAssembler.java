package rain.check.backend.app.infrastructure.assemblers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import rain.check.backend.app.domain.entities.User;
import rain.check.backend.app.interfaceadapters.boundary.UserCreationRequest;
import rain.check.backend.database.domain.entities.UserJPA;

/**
 * User entity assembler.
 */
@Mapper(componentModel = "cdi",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserAssembler {

    @Mapping(target = "city", ignore = true)
    User userCreationRequestToUser(final UserCreationRequest userCreationRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city", ignore = true)
    UserJPA userToUserJPA(final User user);
}
