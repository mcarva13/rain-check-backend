package rain.check.backend.database.applicationservices.implementation;

import jakarta.enterprise.context.Dependent;
import rain.check.backend.database.applicationservices.UserRepository;
import rain.check.backend.database.domain.entities.CityJPA;
import rain.check.backend.database.domain.entities.UserJPA;

import java.util.UUID;

@Dependent
public class UserRepositoryImpl implements UserRepository {

    public UserJPA getUserFromDB(final String userId) {
        final UserJPA userJPA = UserJPA.findById(UUID.fromString(userId));
        return (UserJPA) returnObjectOrThrowNotFoundException(userJPA, "User not found in the database");
    }

    public CityJPA getUserCityFromDB(final UserJPA user) {
        final CityJPA userCity = user.getCity();
        return (CityJPA) returnObjectOrThrowNotFoundException(userCity, "User doesn't have an associated city");
    }

}
