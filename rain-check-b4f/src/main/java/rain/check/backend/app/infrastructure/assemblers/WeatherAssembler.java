package rain.check.backend.app.infrastructure.assemblers;

import rain.check.backend.app.domain.entities.Weather;
import rain.check.backend.app.infrastructure.datamodel.WeatherWS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Weather entity assembler.
 */
@Mapper(componentModel = "cdi",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface WeatherAssembler {

    /**
     * Maps a {@link WeatherWS} response from an external service into a {@link Weather} object.
     * @param weatherWS {@link WeatherWS} object.
     * @return a {@link Weather} object.
     */
    @Mapping(target = "hourlyWeather", source = "hourly")
    @Mapping(target = "currentWeather", source = "current")
    @Mapping(target = "city", ignore = true)
    Weather weatherWSToWeather(final WeatherWS weatherWS);
}
