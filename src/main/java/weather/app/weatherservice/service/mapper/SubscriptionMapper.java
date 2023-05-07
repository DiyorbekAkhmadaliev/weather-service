package weather.app.weatherservice.service.mapper;

import org.mapstruct.Mapper;
import weather.app.weatherservice.dto.SubscriptionDto;
import weather.app.weatherservice.model.Subscriptions;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper extends CommonMapper<SubscriptionDto, Subscriptions> {
}
