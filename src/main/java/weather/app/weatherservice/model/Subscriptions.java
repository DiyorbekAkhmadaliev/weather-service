package weather.app.weatherservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscriptions {

    @Id
    private Integer id;
    private Integer cityId;
    private Integer userId;
    private LocalDateTime subscribedAt;
}
