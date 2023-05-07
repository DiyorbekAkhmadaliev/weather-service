package weather.app.weatherservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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
    public Mono<ResponseDto<CityDto>> addCity(@RequestBody CityDto cityDto) {
        return cityService.addCity(cityDto);
    }

    @GetMapping("cities-list")
    public Flux<CityDto> getAllCities() {
        return cityService.getAllCities();
    }

    @PatchMapping
    public Mono<ResponseDto<CityDto>> editCity(@RequestBody CityDto cityDto) {
        return cityService.editCity(cityDto);
    }

    @GetMapping("by-id/{id}")
    public Mono<ResponseDto<CityDto>> getCityById(@PathVariable Integer id) {
        return cityService.getCityById(id);
    }
}
