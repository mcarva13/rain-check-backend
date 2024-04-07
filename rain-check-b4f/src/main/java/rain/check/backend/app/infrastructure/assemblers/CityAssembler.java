package rain.check.backend.app.infrastructure.assemblers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import rain.check.backend.app.domain.entities.City;
import rain.check.backend.app.infrastructure.datamodel.CityWS;
import rain.check.backend.database.domain.entities.CityJPA;

/**
 * City entity assembler.
 */
@Mapper(componentModel = "cdi",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CityAssembler {

    @Mapping(target = "cityName", source = "name")
    City cityWSToCity(final CityWS cityWS);

    @Mapping(target = "id", ignore = true)
    CityJPA cityToCityJPA(final City city);

    City cityJPAToCity(final CityJPA cityJPA);
}
