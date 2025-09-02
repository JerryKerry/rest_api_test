import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class DeleteUserTest extends TestBase{

    @Test
    void DeleteUser() {
        given()
                .header("x-api-key", apiKey)
                .contentType(JSON)
                .log().uri()
                .when()
                .delete("/users/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}