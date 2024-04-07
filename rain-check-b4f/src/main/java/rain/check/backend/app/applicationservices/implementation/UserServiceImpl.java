package rain.check.backend.app.applicationservices.implementation;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.InternalServerErrorException;
import rain.check.backend.app.applicationservices.CityService;
import rain.check.backend.app.applicationservices.UserService;
import rain.check.backend.app.domain.entities.City;
import rain.check.backend.app.domain.entities.User;
import rain.check.backend.app.infrastructure.assemblers.CityAssembler;
import rain.check.backend.app.infrastructure.assemblers.UserAssembler;
import rain.check.backend.app.interfaceadapters.boundary.UserCreationRequest;
import rain.check.backend.database.applicationservices.CityRepository;
import rain.check.backend.database.applicationservices.UserRepository;
import rain.check.backend.database.domain.entities.CityJPA;
import rain.check.backend.database.domain.entities.UserJPA;

/**
 * User service implementation.
 */
@Dependent
public class UserServiceImpl implements UserService {

    @Inject
    CityService cityService;

    @Inject
    UserRepository userRepository;

    @Inject
    CityRepository cityRepository;

    @Inject
    CityAssembler cityAssembler;

    @Inject
    UserAssembler userAssembler;

    @Override
    public User createNewUser(UserCreationRequest userCreationRequest) {
        // 1 - Check if City exists on Database. If not create new one

        if(!cityRepository.existsByName(userCreationRequest.getCityName())) {
            cityService.createNewCity(userCreationRequest.getCityName());
        }

        final CityJPA cityJPA = cityRepository.findCityByName(userCreationRequest.getCityName());
        final City city = cityAssembler.cityJPAToCity(cityJPA);

        // 2 - Create New User and Associate its city
        if(userRepository.userExists(userCreationRequest.getEmail())) {
            throw new InternalServerErrorException("User already exists on the database");
        }

        final User user = userAssembler.userCreationRequestToUser(userCreationRequest);
        user.setCity(city);

        final UserJPA userJPA = userAssembler.userToUserJPA(user);
        userJPA.setCity(cityJPA);

        userRepository.createNewUser(userJPA);

        return user;
    }
}
