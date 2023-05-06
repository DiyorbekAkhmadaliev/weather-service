package weather.app.weatherservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import weather.app.weatherservice.dto.CityDto;
import weather.app.weatherservice.dto.ResponseDto;
import weather.app.weatherservice.service.CityService;

@RestController
@RequestMapping("city")
@RequiredArgsConstructor
public class CityResources {

    private final CityService cityService;

    @PostMapping
    public Mono<ResponseDto<CityDto>> addCity(@RequestBody CityDto cityDto){
        return cityService.addCity(cityDto);
    }
}
