package rain.check.backend.database.applicationservices.implementation;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.Dependent;
import rain.check.backend.database.applicationservices.UserRepository;
import rain.check.backend.database.domain.entities.CityJPA;
import rain.check.backend.database.domain.entities.UserJPA;

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
        final UserJPA userJPA = PanacheEntityBase.findById(UUID.fromString(userId));
        return (UserJPA) returnObjectOrThrowNotFoundException(userJPA, "User not found in the database");
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
