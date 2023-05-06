package weather.app.weatherservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {

    private Integer id;
    private CityDto city;
    private Integer temperature;
    private Integer humidity;
    private Integer wind;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime date;
}

