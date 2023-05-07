package weather.app.weatherservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import weather.app.weatherservice.model.UserSession;

@Repository
public interface UserSessionRepository extends ReactiveCrudRepository<UserSession, String> {
}
