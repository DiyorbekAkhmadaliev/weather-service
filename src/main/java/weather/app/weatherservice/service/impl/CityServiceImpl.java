package weather.app.weatherservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import weather.app.weatherservice.dto.CityDto;
import weather.app.weatherservice.dto.ResponseDto;
import weather.app.weatherservice.repository.CityRepository;
import weather.app.weatherservice.service.CityService;
import weather.app.weatherservice.service.mapper.CityMapper;

import static weather.app.weatherservice.service.validator.AppStatusMessages.*;
import static weather.app.weatherservice.service.validator.AppStatusCodes.*;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public Mono<ResponseDto<CityDto>> addCity(CityDto cityDto) {
        if (cityDto == null) {
            return Mono.just(ResponseDto.<CityDto>builder()
                    .message(NULL_VALUE)
                    .code(VALIDATION_ERROR_CODE)
                    .build());
        }
        return cityRepository.findByName(cityDto.getName())
                .map(city -> ResponseDto.<CityDto>builder()
                        .message(DUPLICATE_ERROR + " City already exists!")
                        .code(VALIDATION_ERROR_CODE)
                        .build())
                .switchIfEmpty(cityRepository.save(cityMapper.toEntity(cityDto))
                        .map(savedCity -> ResponseDto.<CityDto>builder()
                                .message(OK)
                                .code(OK_CODE)
                                .success(true)
                                .data(cityMapper.toDto(savedCity))
                                .build()));
    }

    @Override
    public Mono<ResponseDto<CityDto>> getCityById(Integer id) {
        return cityRepository.findById(id)
                .map(city -> ResponseDto.<CityDto>builder()
                        .message(OK)
                        .code(OK_CODE)
                        .success(true)
                        .data(cityMapper.toDto(city))
                        .build())
                .switchIfEmpty(Mono.just(ResponseDto.<CityDto>builder()
                        .message(NOT_FOUND)
                        .code(NOT_FOUND_ERROR_CODE)
                        .build()));

    }

    @Override
    public Mono<ResponseDto<CityDto>> editCity(CityDto cityDto) {
        return cityRepository.findById(cityDto.getId())
                .flatMap(city -> {
                    if (cityDto.getName() != null) {
                        city.setName(cityDto.getName());
                    }
                    if (cityDto.getCountry() != null) {
                        city.setCountry(cityDto.getCountry());
                    }

                    return cityRepository.save(city)
                            .map(editedCity -> ResponseDto.<CityDto>builder()
                                    .message(OK)
                                    .code(OK_CODE)
                                    .success(true)
                                    .data(cityMapper.toDto(editedCity))
                                    .build())
                            .onErrorResume(throwable -> Mono.just(
                                    ResponseDto.<CityDto>builder()
                                            .message(DATABASE_ERROR)
                                            .code(DATABASE_ERROR_CODE)
                                            .build()));
                }).switchIfEmpty(Mono.just(
                        ResponseDto.<CityDto>builder()
                                .message(NOT_FOUND)
                                .code(NOT_FOUND_ERROR_CODE)
                                .build()));
    }

    @Override
    public Flux<CityDto> getAllCities() {
        return cityRepository.findAll()
                .map(cityMapper::toDto);
    }

    @Override
    public Mono<ResponseDto<CityDto>> removeCityById(Integer id) {
        return cityRepository.deleteById(id)
                .map(c -> ResponseDto.<CityDto>builder()
                        .message(OK)
                        .code(OK_CODE)
                        .success(true)
                        .build());
    }
}
