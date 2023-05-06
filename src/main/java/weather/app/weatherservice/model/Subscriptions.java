package weather.app.weatherservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscriptions {

    @Id
    private Integer id;
    private Integer cityId;
    private Integer userId;
    private LocalDateTime subscribedAt;
}
