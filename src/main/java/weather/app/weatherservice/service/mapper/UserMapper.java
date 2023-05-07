package weather.app.weatherservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import weather.app.weatherservice.dto.UserDto;
import weather.app.weatherservice.model.User;

@Mapper(componentModel = "spring")
public abstract class UserMapper implements CommonMapper<UserDto, User> {

    @Autowired
    protected PasswordEncoder encoder;

    @Mapping(target = "enabled", expression = "java(true)")
    @Mapping(target = "password", expression = "java(encoder.encode(dto.getPassword()))")
    public abstract User toEntity(UserDto dto);
}
