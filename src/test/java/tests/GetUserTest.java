package tests;

import dto.request.RequestGetUserIdDto;
import dto.response.ResponseGetUserIdDto;
import org.junit.jupiter.api.Test;

import static data.Message.SUPPORT_TEXT;
import static data.Message.SUPPORT_URL;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;


public class GetUserTest extends TestBase {


    @Test
    void successfulSingleUserTest() {
        RequestGetUserIdDto requestGetUserIdDto = new RequestGetUserIdDto();
        requestGetUserIdDto.setId(2);
        Integer id = requestGetUserIdDto.getId();

        ResponseGetUserIdDto response = step("Request", () ->
                given(requestSpec)
                        .params("id", id)
                        .get("/users/")
                        .then()
                        .spec(responseSpec(200)).extract().as(ResponseGetUserIdDto.class)
        );
        step("check response", () -> {
            assertEquals(2, response.getData().getId());
            assertEquals("janet.weaver@reqres.in", response.getData().getEmail());
            assertEquals("Janet", response.getData().getFirstName());
            assertEquals("Weaver", response.getData().getLastName());
            assertEquals(baseURI + "/img/faces/2-image.jpg", response.getData().getAvatar());
            assertEquals(SUPPORT_URL, response.getSupport().getUrl());
            assertEquals(SUPPORT_TEXT, response.getSupport().getText());
        });

    }
}
