package tests;

import dto.request.RequestPostRegistrationDto;
import dto.response.ErrorResponseDto;
import dto.response.ResponseRegistrationDto;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;

public class RegistrationTest extends TestBase {

    RequestPostRegistrationDto registrationDto = new RequestPostRegistrationDto();


    @Test
    void successRegisterUser() {
        registrationDto.setEmail("eve.holt@reqres.in");
        registrationDto.setPassword("pistol");

        ResponseRegistrationDto response = step("Request", () ->
                given(requestSpec)
                        .body(registrationDto)
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec(200))
                        .extract()
                        .as(ResponseRegistrationDto.class)
        );
        step("Check response", () -> {
            assertEquals(4, response.getId());
            assertNotNull(response.getToken(), "Token is null");
        });

    }

    @Test
    void unsuccessLogin() {

        registrationDto.setEmail("asdfgfdgdfg");

        ErrorResponseDto response = step("Request", () ->
                given(requestSpec)
                        .body(registrationDto)
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec(400))
                        .extract()
                        .as(ErrorResponseDto.class)
        );

        step("Check response", () -> {
            assertNotNull(response.getError(), "Missing password");

        });
    }
}