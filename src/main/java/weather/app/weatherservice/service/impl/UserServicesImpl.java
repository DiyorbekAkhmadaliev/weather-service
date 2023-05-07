package weather.app.weatherservice.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import weather.app.weatherservice.dto.LoginDto;
import weather.app.weatherservice.dto.ResponseDto;
import weather.app.weatherservice.dto.SubscriptionDto;
import weather.app.weatherservice.dto.UserDto;
import weather.app.weatherservice.model.Subscriptions;
import weather.app.weatherservice.model.User;
import weather.app.weatherservice.repository.CityRepository;
import weather.app.weatherservice.repository.SubscriptionRepository;
import weather.app.weatherservice.repository.UserRepository;
import weather.app.weatherservice.security.JwtService;
import weather.app.weatherservice.service.UserServices;
import weather.app.weatherservice.service.mapper.SubscriptionMapper;
import weather.app.weatherservice.service.mapper.UserMapper;
import weather.app.weatherservice.service.validator.AppStatusCodes;
import weather.app.weatherservice.service.validator.AppStatusMessages;

import java.time.LocalDateTime;

import static weather.app.weatherservice.service.validator.AppStatusCodes.*;
import static weather.app.weatherservice.service.validator.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices, ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final Gson gson;
    private final SubscriptionRepository subscriptionRepository;
    private final CityRepository cityRepository;
    private final SubscriptionMapper subscriptionMapper;


    @Override
    public Mono<ResponseDto<UserDto>> addUser(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail())
                .flatMap(user ->
                        {
                            if (!user.isEnabled()) {
                                user.setEnabled(true);
                                return Mono.just(
                                        ResponseDto.<UserDto>builder()
                                                .message(AppStatusMessages.OK)
                                                .code(OK_CODE)
                                                .success(true)
                                                .data(userMapper.toDto(user))
                                                .build());
                            } else {
                                return Mono.just(
                                        ResponseDto.<UserDto>builder()
                                                .message(DUPLICATE_ERROR + " User already exists!")
                                                .code(VALIDATION_ERROR_CODE)
                                                .build()
                                );
                            }
                        }
                ).switchIfEmpty(Mono.defer(() -> userRepository.save(userMapper.toEntity(userDto))
                        .map(savedUser -> ResponseDto.<UserDto>builder()
                                .message(AppStatusMessages.OK)
                                .code(OK_CODE)
                                .success(true)
                                .data(userMapper.toDto(savedUser))
                                .build()))
                );
    }

    @Override
    public Mono<ResponseDto<UserDto>> editUser(UserDto userDto) {
        if (userDto.getId() == null) {
            return Mono.just(ResponseDto.<UserDto>builder()
                    .message(NULL_VALUE + " User Id is null!")
                    .code(VALIDATION_ERROR_CODE)
                    .build());
        }
        return userRepository.findByIdAndEnabled(userDto.getId(), true)
                .flatMap(user -> {
                    if (userDto.getFirstName() != null) {
                        user.setFirstName(userDto.getFirstName());
                    }
                    if (userDto.getLastName() != null) {
                        user.setLastName(userDto.getLastName());
                    }
                    if (userDto.getEmail() != null) {
                        user.setEmail(userDto.getEmail());
                    }
                    if (userDto.getPassword() != null) {
                        user.setPassword(userDto.getPassword());
                    }
                    user.setUpdatedAt(LocalDateTime.now());

                    return userRepository.save(user)
                            .map(editedUser -> ResponseDto.<UserDto>builder()
                                    .message(OK)
                                    .code(OK_CODE)
                                    .success(true)
                                    .data(userMapper.toDto(editedUser))
                                    .build()
                            ).onErrorResume(throwable -> Mono.just(
                                    ResponseDto.<UserDto>builder()
                                            .message(DATABASE_ERROR)
                                            .code(DATABASE_ERROR_CODE)
                                            .build()
                            ));
                }).switchIfEmpty(Mono.just(ResponseDto.<UserDto>builder()
                        .message(NOT_FOUND)
                        .code(NOT_FOUND_ERROR_CODE)
                        .build()));
    }

    @Override
    public Flux<UserDto> getAllUsers() {
        return userRepository.findAll()
                .map(userMapper::toDto);
    }

    @Override
    public Mono<ResponseDto<UserDto>> getUserById(Integer id) {
        return userRepository.findByIdAndEnabled(id, true)
                .map(user -> ResponseDto.<UserDto>builder()
                        .message(OK)
                        .code(OK_CODE)
                        .success(true)
                        .data(userMapper.toDto(user))
                        .build())
                .switchIfEmpty(Mono.just(ResponseDto.<UserDto>builder()
                        .message(NOT_FOUND)
                        .code(NOT_FOUND_ERROR_CODE)
                        .build()));
    }

    @Override
    public Mono<ResponseDto<String>> login(LoginDto loginDto) {
        return userRepository.findByEmailAndEnabled(loginDto.getEmail(), true)
                .filter(user -> passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
                .map(user -> ResponseDto.<String>builder()
                        .code(OK_CODE)
                        .message(OK)
                        .success(true)
                        .data(jwtService.generateToken(gson.toJson(user), user.getAuthorities()))
                        .build())
                .defaultIfEmpty(ResponseDto.<String>builder()
                        .code(VALIDATION_ERROR_CODE)
                        .message(VALIDATION_ERROR)
                        .build());
    }

    @Override
    public Mono<ResponseDto<SubscriptionDto>> subscribeToCity(Integer cityId) {
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(user -> {
                    User principal = (User) user.getAuthentication().getPrincipal();
                    return subscriptionRepository.save(Subscriptions.builder()
                                    .userId(principal.getId())
                                    .cityId(cityId)
                                    .subscribedAt(LocalDateTime.now())
                                    .build())
                            .map(s -> ResponseDto.<SubscriptionDto>builder()
                                    .code(OK_CODE)
                                    .message(OK)
                                    .success(true)
                                    .build());
                });
    }


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return null;
    }
}
