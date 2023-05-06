package weather.app.weatherservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {

    private int code;
    private String message;
    private boolean success;
    private T data;

}
