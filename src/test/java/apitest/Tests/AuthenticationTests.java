package apitest.Tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationTests {

    private static final String BASE_URL =
            "https://restful-booker.herokuapp.com";

    @Test(description = "Verify successful token generation")
    public void verifyGenerateToken() {

        String requestBody = """
                {
                    "username":"admin",
                    "password":"password123"
                }
                """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post(BASE_URL + "/auth");

        Assert.assertEquals(response.statusCode(), 200);

        String token =
                response.jsonPath().getString("token");

        Assert.assertNotNull(token);
        Assert.assertFalse(token.isEmpty());

        System.out.println("Generated Token: " + token);
    }

    @Test(description = "Verify authentication with invalid username")
    public void verifyInvalidUsername() {

        String requestBody = """
                {
                    "username":"invalidUser",
                    "password":"password123"
                }
                """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post(BASE_URL + "/auth");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(
                response.jsonPath().getString("reason"),
                "Bad credentials");
    }

    @Test(description = "Verify authentication with invalid password")
    public void verifyInvalidPassword() {

        String requestBody = """
                {
                    "username":"admin",
                    "password":"wrongPassword"
                }
                """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post(BASE_URL + "/auth");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(
                response.jsonPath().getString("reason"),
                "Bad credentials");
    }

    @Test(description = "Verify authentication with blank credentials")
    public void verifyBlankCredentials() {

        String requestBody = """
                {
                    "username":"",
                    "password":""
                }
                """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post(BASE_URL + "/auth");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(
                response.jsonPath().getString("reason"),
                "Bad credentials");
    }

    @Test(description = "Verify authentication response contains token")
    public void verifyTokenLength() {

        String requestBody = """
                {
                    "username":"admin",
                    "password":"password123"
                }
                """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post(BASE_URL + "/auth");

        String token =
                response.jsonPath().getString("token");

        Assert.assertNotNull(token);
        Assert.assertTrue(token.length() > 5,
                "Token length validation failed");
    }
}