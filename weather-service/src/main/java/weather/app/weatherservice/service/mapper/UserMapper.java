package weather.app.weatherservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import weather.app.weatherservice.dto.UserDto;
import weather.app.weatherservice.model.User;

@Mapper(componentModel = "spring")
public abstract class UserMapper implements CommonMapper<UserDto, User> {
    @Mapping(target = "enabled", expression = "java(true)")
    public abstract User toEntity(UserDto dto);
}
