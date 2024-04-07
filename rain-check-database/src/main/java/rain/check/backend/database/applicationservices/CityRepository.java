package rain.check.backend.database.applicationservices;

import rain.check.backend.database.domain.entities.CityJPA;

/**
 * Database {@link CityJPA} repository services.
 */
public interface CityRepository extends Repository {

    CityJPA findCityByName(String name);

    boolean existsByName(String name);

    void createNewCity(CityJPA city);
}
