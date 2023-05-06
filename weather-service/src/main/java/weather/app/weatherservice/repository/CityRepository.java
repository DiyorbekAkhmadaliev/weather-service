package weather.app.weatherservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import weather.app.weatherservice.model.City;

@Repository
public interface CityRepository extends ReactiveCrudRepository<City, Integer> {
}
