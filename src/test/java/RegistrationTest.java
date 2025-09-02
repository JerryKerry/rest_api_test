import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class RegistrationTest extends TestBase {

    @Test
    void successRegisterUser() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"134\"}";

        given()
                .header("x-api-key", apiKey)
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", not(isEmptyOrNullString()));
    }

    @Test
    void unsuccessLogin() {
        String authData = "{\"email\": \"asdfgfdgdfg\"}";

        given()
                .header("x-api-key", apiKey)
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}