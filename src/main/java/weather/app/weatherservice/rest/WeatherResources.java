package weather.app.weatherservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import weather.app.weatherservice.dto.ResponseDto;
import weather.app.weatherservice.dto.WeatherDto;
import weather.app.weatherservice.service.WeatherServices;

@RestController
@RequestMapping("weather")
@RequiredArgsConstructor
public class WeatherResources {

    private final WeatherServices weatherServices;

    @PostMapping
    public Mono<ResponseDto<WeatherDto>> updateCityWeather(@RequestBody WeatherDto weatherDto){
        return weatherServices.updateCityWeather(weatherDto);
    }
}
