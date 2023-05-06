package weather.app.weatherservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import weather.app.weatherservice.dto.CityDto;
import weather.app.weatherservice.model.City;

@Mapper(componentModel = "spring")
public abstract class CityMapper implements CommonMapper<CityDto, City>{
    @Mapping(target = "isVisible", expression = "java(true)")
    public abstract City toEntity(CityDto cityDto);
}
