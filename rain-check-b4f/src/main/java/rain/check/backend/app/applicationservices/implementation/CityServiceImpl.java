package rain.check.backend.app.applicationservices.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.InternalServerErrorException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import rain.check.backend.app.applicationservices.CityService;
import rain.check.backend.app.domain.entities.City;
import rain.check.backend.app.infrastructure.ExternalGeolocationService;
import rain.check.backend.app.infrastructure.assemblers.CityAssembler;
import rain.check.backend.app.infrastructure.datamodel.CityWS;
import rain.check.backend.app.infrastructure.datamodel.CityWSResponse;
import rain.check.backend.database.applicationservices.CityRepository;

@Dependent
public class CityServiceImpl implements CityService {

    @Inject
    CityRepository cityRepository;

    @Inject
    CityAssembler cityAssembler;

    @RestClient
    private ExternalGeolocationService externalGeolocationService;

    @Override
    public City createNewCity(String cityName) {
        final CityWS cityWS = getCityFromExternalService(cityName);
        final City city = cityAssembler.cityWSToCity(cityWS);

        cityRepository.createNewCity(cityAssembler.cityToCityJPA(city));

        return city;
    }

    private CityWS getCityFromExternalService(final String cityName) {
        String json = externalGeolocationService.getCityGeolocationInfo(cityName,
                ExternalGeolocationService.NUMBER_OF_CITY_COUNT).readEntity(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CityWSResponse response = objectMapper.readValue(json, CityWSResponse.class);

            // Get the first city from the results
            return response.getResults().get(0);
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException("Couldn't process external service response", e);
        }
        catch (NullPointerException e) {
            throw new InternalServerErrorException("No city found for input " + cityName, e);
        }
    }
}
