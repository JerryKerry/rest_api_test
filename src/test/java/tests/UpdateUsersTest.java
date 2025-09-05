package tests;

import dto.request.RequestPutUserIdDto;
import dto.response.ResponsePutUserIdDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;

public class UpdateUsersTest extends TestBase {

    RequestPutUserIdDto request = new RequestPutUserIdDto();

    @Test
    void successfulUpdateUserTest() {

        request.setJob("QA");
        request.setName("Boba");
        String currentDate = LocalDate.now().toString();

        ResponsePutUserIdDto response = step("Request", () ->

                given(requestSpec)
                        .body(request)
                        .when()
                        .put("/users/2")
                        .then().spec(responseSpec(200))
                        .extract().response().as(ResponsePutUserIdDto.class)
        );

        step("Check response", () -> {
            assertEquals(currentDate, response.getUpdatedAt().substring(0, 10));
            assertEquals(request.getJob(), response.getJob());
            assertEquals(request.getName(), response.getName());

        });
    }
}