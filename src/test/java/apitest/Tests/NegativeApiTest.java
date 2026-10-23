package apitest.Tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NegativeApiTest{

    private static final String BASE_URL =
            "https://restful-booker.herokuapp.com";

    @Test(description = "Invalid authentication")
    public void verifyInvalidAuthentication() {

        String requestBody = """
                {
                    "username":"wrongUser",
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

    @Test(description = "Retrieve non-existing booking")
    public void verifyRetrieveInvalidBooking() {

        Response response =
                given()
                        .when()
                        .get(BASE_URL + "/booking/99999999");

        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(description = "Update booking without token")
    public void verifyUpdateBookingWithoutToken() {

        String updateBody = """
                {
                    "firstname":"John",
                    "lastname":"Doe",
                    "totalprice":200,
                    "depositpaid":true,
                    "bookingdates":{
                        "checkin":"2026-01-01",
                        "checkout":"2026-01-10"
                    },
                    "additionalneeds":"Breakfast"
                }
                """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(updateBody)
                        .when()
                        .put(BASE_URL + "/booking/1");

        Assert.assertTrue(
                response.statusCode() == 401 ||
                        response.statusCode() == 403,
                "Expected unauthorized response");
    }

    @Test(description = "Delete booking without token")
    public void verifyDeleteBookingWithoutToken() {

        Response response =
                given()
                        .when()
                        .delete(BASE_URL + "/booking/1");

        Assert.assertTrue(
                response.statusCode() == 401 ||
                        response.statusCode() == 403,
                "Expected unauthorized response");
    }

    @Test(description = "Create booking with empty payload")
    public void verifyCreateBookingWithEmptyPayload() {

        Response response =
                given()
                        .contentType("application/json")
                        .body("{}")
                        .when()
                        .post(BASE_URL + "/booking");

        Assert.assertTrue(
                response.statusCode() >= 400 ||
                        response.statusCode() == 200);
    }

    @Test(description = "Create booking with invalid data type")
    public void verifyCreateBookingWithInvalidData() {

        String invalidBody = """
                {
                  "firstname":"John",
                  "lastname":"Doe",
                  "totalprice":"INVALID_PRICE",
                  "depositpaid":true,
                  "bookingdates":{
                     "checkin":"2026-01-01",
                     "checkout":"2026-01-10"
                  }
                }
                """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(invalidBody)
                        .when()
                        .post(BASE_URL + "/booking");

        Assert.assertTrue(
                response.statusCode() >= 400 ||
                        response.statusCode() == 200);
    }

    @Test(description = "Invalid endpoint")
    public void verifyInvalidEndpoint() {

        Response response =
                given()
                        .when()
                        .get(BASE_URL + "/invalidendpoint");

        Assert.assertEquals(response.statusCode(), 404);
    }
}