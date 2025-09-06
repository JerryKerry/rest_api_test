package tests;

import dto.request.RequestDeleteUserDto;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;

public class DeleteUserTest extends TestBase {

    @Test
    void deleteUserTest() {
        RequestDeleteUserDto request = new RequestDeleteUserDto();
        request.setId(1);
        Integer id = request.getId();

        step("Response", () ->
                given(requestSpec)
                        .when()
                        .delete("/users/{id}", id)
                        .then()
                        .spec(responseSpec(204))
        );
    }
}