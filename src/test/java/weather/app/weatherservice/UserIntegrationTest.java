package weather.app.weatherservice;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import weather.app.weatherservice.dto.LoginDto;
import weather.app.weatherservice.dto.ResponseDto;
import weather.app.weatherservice.dto.UserDto;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;



    @Test
    @Order(1)
//    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/sql/delete-test-value-addUserTest.sql")
    public void addUserTest() throws Exception {

        UserDto userDto = UserDto.builder()
                .firstName("TestUser")
                .lastName("UserTest")
                .email("testuser@gmail.com")
                .password("test1234")
                .role("USER")
                .build();

        webTestClient.post().uri("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userDto)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @Order(2)
    public void loginTest(){

        LoginDto loginDto = LoginDto.builder()
                .email("testuser@gmail.com")
                .password("test1234")
                .build();

        webTestClient.post().uri("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseDto.class)
                .value(responseDto -> {
                    assertThat(responseDto.getCode()).isEqualTo(0);
                    assertThat(responseDto.getMessage()).isEqualTo("OK");
                    assertThat(responseDto.isSuccess()).isTrue();
                });
    }
}
