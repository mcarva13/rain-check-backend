package rain.check.backend.database.applicationservices;

import rain.check.backend.database.domain.entities.CityJPA;
import rain.check.backend.database.domain.entities.UserJPA;

/**
 * Database {@link UserJPA} repository services.
 */
public interface UserRepository extends Repository {

    /**
     * Gets the User from the database that matches the input id.
     * @param userId - User Id.
     * @return a {@link UserJPA} object.
     */
    UserJPA getUserFromDB(final String userId);

    /**
     * Gets the City from the database that is associated with a User.
     * @param user - the {@link UserJPA} on the database.
     * @return a {@link CityJPA} object.
     */
    CityJPA getUserCityFromDB(final UserJPA user);

}
