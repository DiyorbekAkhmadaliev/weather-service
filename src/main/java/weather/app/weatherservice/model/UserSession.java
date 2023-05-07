package weather.app.weatherservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {

    @Id
    private String uuid;
    private String userInfo;
}
