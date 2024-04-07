package rain.check.backend.app.applicationservices;

import rain.check.backend.app.domain.entities.City;

/**
 * City service.
 */
public interface CityService {

    City createNewCity(String city);
}
