package weather.app.weatherservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;
import weather.app.weatherservice.dto.CityDto;
import weather.app.weatherservice.dto.ResponseDto;
import weather.app.weatherservice.dto.WeatherDto;
import weather.app.weatherservice.repository.CityRepository;
import weather.app.weatherservice.repository.WeatherRepository;
import weather.app.weatherservice.service.WeatherServices;
import weather.app.weatherservice.service.mapper.CityMapper;
import weather.app.weatherservice.service.mapper.WeatherMapper;
import weather.app.weatherservice.service.validator.AppStatusCodes;
import weather.app.weatherservice.service.validator.AppStatusMessages;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherServices {

    private final WeatherRepository weatherRepository;
    private final CityRepository cityRepository;
    private final WeatherMapper weatherMapper;
    private final CityMapper cityMapper;

    @Override
    public Mono<ResponseDto<WeatherDto>> updateCityWeather(@RequestBody WeatherDto weatherDto) {
        return weatherRepository.save(weatherMapper.toEntity(weatherDto))
                .flatMap(weather -> loadCity(weatherDto)
                        .map(weatherDto1 -> ResponseDto.<WeatherDto>builder()
                        .message(AppStatusMessages.OK)
                        .code(AppStatusCodes.OK_CODE)
                        .success(true)
                        .data(weatherDto1)
                        .build()));
    }

    private Mono<WeatherDto> loadCity(WeatherDto dto) {
        return Mono.just(dto)
                .flatMap(weatherDto -> cityRepository.findById(weatherDto.getCity().getId())
                        .map(city -> {
                            weatherDto.setCity(cityMapper.toDto(city));
                            return weatherDto;
                        }));
    }

}
