package rain.check.backend.app.infrastructure.assemblers;

import rain.check.backend.app.domain.entities.City;
import rain.check.backend.app.domain.entities.Weather;
import rain.check.backend.app.infrastructure.datamodel.WeatherWS;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "cdi",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface WeatherAssembler {

    @Mapping(target = "hourlyPrecipitation", source = "hourly")
    @Mapping(target = "currentPrecipitation", source = "current")
    @Mapping(target = "city", ignore = true)
    Weather weatherWSToWeather(final WeatherWS weatherWS);

    @AfterMapping
    default void addCity(WeatherWS weatherWS,
                         @MappingTarget Weather.WeatherBuilder result) {
        result.city(City.builder()
                .latitude(weatherWS.getLatitude())
                .longitude(weatherWS.getLongitude())
                .build());
    }

}
