package weather.app.weatherservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import weather.app.weatherservice.dto.CityDto;
import weather.app.weatherservice.model.City;

@Repository
public interface CityRepository extends ReactiveCrudRepository<City, Integer> {

    Mono<CityDto> findByName(String name);

    Mono<City> findByIdAndVisible(Integer id, boolean visible);
}
