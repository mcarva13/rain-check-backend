package rain.check.backend.database.applicationservices.implementation;

import jakarta.enterprise.context.Dependent;
import jakarta.transaction.Transactional;
import rain.check.backend.database.applicationservices.UserRepository;
import rain.check.backend.database.domain.entities.CityJPA;
import rain.check.backend.database.domain.entities.UserJPA;

import java.util.Objects;
import java.util.UUID;

/**
 * {@link UserRepository} implementation.
 */
@Dependent
public class UserRepositoryImpl implements UserRepository {

    /**
     * {@inheritDoc}
     */
    @Override
    public UserJPA getUserFromDB(final String userId) {
        final UserJPA userJPA = UserJPA.findById(UUID.fromString(userId));
        return (UserJPA) returnObjectOrThrowNotFoundException(userJPA, "User not found in the database");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void createNewUser(UserJPA user) {
        UserJPA.persist(user);
    }

    @Override
    public boolean userExists(String email) {
        return Objects.nonNull(UserJPA.findByEmail(email));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CityJPA getUserCityFromDB(final UserJPA user) {
        final CityJPA userCity = user.getCity();
        return (CityJPA) returnObjectOrThrowNotFoundException(userCity, "User doesn't have an associated city");
    }

}
