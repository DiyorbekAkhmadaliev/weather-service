package weather.app.weatherservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import weather.app.weatherservice.model.Subscriptions;

@Repository
public interface SubscriptionRepository extends ReactiveCrudRepository<Subscriptions, Integer> {
}
