package rain.check.backend.database.applicationservices.implementation;

import jakarta.enterprise.context.Dependent;
import jakarta.transaction.Transactional;
import rain.check.backend.database.applicationservices.CityRepository;
import rain.check.backend.database.domain.entities.CityJPA;

import java.util.Objects;

/**
 * {@link CityRepository} implementation.
 */
@Dependent
public class CityRepositoryImpl implements CityRepository {

    @Override
    public CityJPA findCityByName(String name) {
        final CityJPA cityJPA = CityJPA.findByName(name);
        return (CityJPA) returnObjectOrThrowNotFoundException(cityJPA, "City not found in the database");
    }

    @Override
    public boolean existsByName(String name) {
        return Objects.nonNull(CityJPA.findByName(name));
    }

    @Override
    @Transactional
    public void createNewCity(CityJPA city) {
        CityJPA.persist(city);
    }
}
