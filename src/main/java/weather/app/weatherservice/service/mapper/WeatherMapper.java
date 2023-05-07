package weather.app.weatherservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import weather.app.weatherservice.dto.WeatherDto;
import weather.app.weatherservice.model.Weather;

@Mapper(componentModel = "spring")
public interface WeatherMapper extends CommonMapper<WeatherDto, Weather> {
    @Mapping(target = "cityId", expression = "java(weatherDto.getCity().getId())")
    Weather toEntity(WeatherDto weatherDto);
}
