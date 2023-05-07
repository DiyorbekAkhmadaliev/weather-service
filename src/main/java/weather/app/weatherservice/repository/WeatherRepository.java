package weather.app.weatherservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import weather.app.weatherservice.model.Weather;

@Repository
public interface WeatherRepository extends ReactiveCrudRepository<Weather, Integer> {
}
