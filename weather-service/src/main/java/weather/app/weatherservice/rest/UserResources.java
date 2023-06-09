package weather.app.weatherservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import weather.app.weatherservice.dto.ResponseDto;
import weather.app.weatherservice.dto.UserDto;
import weather.app.weatherservice.service.UserServices;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserResources {

    private final UserServices userServices;

    @PostMapping("sign-up")
    public Mono<ResponseDto<UserDto>> addUser(@RequestBody UserDto userDto){
         return userServices.addUser(userDto);
    }

    @GetMapping("user-list")
    public Flux<UserDto> getAllUsers(){
        return userServices.getAllUsers();
    }

    @GetMapping("by-id/{id}")
    public Mono<ResponseDto<UserDto>> getUserById(@PathVariable Integer id){
        return userServices.getUserById(id);
    }

    @PatchMapping("edit-user")
    public Mono<ResponseDto<UserDto>> editUser(@RequestBody UserDto userDto){
        return userServices.editUser(userDto);
    }
}
