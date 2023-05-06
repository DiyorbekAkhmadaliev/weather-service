package weather.app.weatherservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;
import weather.app.weatherservice.dto.CityDto;

import java.time.LocalDateTime;

@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @Id
    private Integer id;
    private Integer cityId;
    private Integer temperature;
    private Integer humidity;
    private Integer wind;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime date;
}
