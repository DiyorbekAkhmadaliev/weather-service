package weather.app.weatherservice.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import weather.app.weatherservice.dto.UserDto;
import weather.app.weatherservice.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-06T21:46:13+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDto toDto(User e) {
        if ( e == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( e.getId() );
        userDto.setFirstName( e.getFirstName() );
        userDto.setLastName( e.getLastName() );
        userDto.setPassword( e.getPassword() );
        userDto.setEmail( e.getEmail() );
        userDto.setCreatedAt( e.getCreatedAt() );
        userDto.setUpdatedAt( e.getUpdatedAt() );

        return userDto;
    }

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setPassword( dto.getPassword() );
        user.setEmail( dto.getEmail() );
        user.setCreatedAt( dto.getCreatedAt() );
        user.setUpdatedAt( dto.getUpdatedAt() );

        user.setEnabled( true );

        return user;
    }
}
