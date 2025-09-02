import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class UpdateUsersTest extends TestBase {

    @Test
    void successfulUpdateUserTest() {
        String
                authData = "{\"name\": \"boba\", \"job\": \"QA\"}",
                currentDate = LocalDate.now().toString();

        given()
                .header("x-api-key", apiKey)
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("boba"))
                .body("job", is("QA"))
                .body("updatedAt", startsWith(currentDate));
    }
}