package weather.app.weatherservice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import weather.app.weatherservice.dto.CityDto;
import weather.app.weatherservice.dto.ResponseDto;

public interface CityService {

    Mono<ResponseDto<CityDto>> addCity(CityDto cityDto);
    Mono<ResponseDto<CityDto>> getCityById(Integer id);
    Mono<ResponseDto<CityDto>> editCity(CityDto cityDto);
    Flux<CityDto> getAllCities();
    Mono<ResponseDto<CityDto>> removeCityById(Integer id);
}
