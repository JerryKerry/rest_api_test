import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GetUserTest extends TestBase {

    String
            Url = "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral",
            Text = "Tired of writing endless social media content? Let Content Caddy generate it for you.";
    @Test
    void successfulSingleUserTest() {
        given()
                .header("x-api-key", apiKey)
                .log().uri()
                .get("/users/5")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(5))
                .body("data.email", is("charles.morris@reqres.in"))
                .body("data.first_name", is("Charles"))
                .body("data.last_name", is("Morris"))
                .body("data.avatar",  is(baseURI + "/img/faces/5-image.jpg"))
                .body("support.url", is(Url))
                .body("support.text", is(Text));
    }

}
