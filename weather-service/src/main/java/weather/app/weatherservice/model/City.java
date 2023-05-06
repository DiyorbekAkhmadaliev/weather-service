package weather.app.weatherservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("cities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @org.springframework.data.annotation.Id
    private Integer Id;
    private String name;
    private String country;
    private boolean isVisible;
}
