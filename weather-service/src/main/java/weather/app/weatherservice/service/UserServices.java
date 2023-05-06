package weather.app.weatherservice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import weather.app.weatherservice.dto.ResponseDto;
import weather.app.weatherservice.dto.UserDto;

import java.util.List;

public interface UserServices {

    Mono<ResponseDto<UserDto>> addUser(UserDto userDto);
    Mono<ResponseDto<UserDto>> editUser(UserDto userDto);
    Flux<UserDto> getAllUsers();
    Mono<ResponseDto<UserDto>> getUserById(Integer id);



}
