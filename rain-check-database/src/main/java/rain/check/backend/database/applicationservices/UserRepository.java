package rain.check.backend.database.applicationservices;

import rain.check.backend.database.domain.entities.CityJPA;
import rain.check.backend.database.domain.entities.UserJPA;

public interface UserRepository extends Repository {

    UserJPA getUserFromDB(final String userId);

    CityJPA getUserCityFromDB(final UserJPA user);

}
