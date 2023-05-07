package weather.app.weatherservice.service;

import reactor.core.publisher.Mono;
import weather.app.weatherservice.dto.ResponseDto;
import weather.app.weatherservice.dto.SubscriptionDto;
import weather.app.weatherservice.dto.WeatherDto;

public interface WeatherServices {

    Mono<ResponseDto<WeatherDto>> updateCityWeather(WeatherDto weatherDto);

}
