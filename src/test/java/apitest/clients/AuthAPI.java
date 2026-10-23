package apitest.clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthAPI {

    public String generateToken() {

        String body = """
            {
                "username":"admin",
                "password":"password123"
            }
            """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(body)
                        .when()
                        .post("/auth");

        return response.jsonPath()
                .getString("token");
    }
}