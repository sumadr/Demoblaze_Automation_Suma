package apitest.Tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTests {

    private static final String BASE_URL =
            "https://restful-booker.herokuapp.com";

    @Test(description = "Create booking with valid data")
    public void verifyCreateBooking() {

        String requestBody = """
                {
                    "firstname":"John",
                    "lastname":"Smith",
                    "totalprice":500,
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
                        .body(requestBody)
                        .when()
                        .post(BASE_URL + "/booking");

        Assert.assertEquals(response.statusCode(), 200);

        int bookingId =
                response.jsonPath().getInt("bookingid");

        Assert.assertTrue(
                bookingId > 0,
                "Booking ID should be generated");

        Assert.assertEquals(
                response.jsonPath().getString("booking.firstname"),
                "John");

        Assert.assertEquals(
                response.jsonPath().getString("booking.lastname"),
                "Smith");

        Assert.assertEquals(
                response.jsonPath().getInt("booking.totalprice"),
                500);

        Assert.assertTrue(
                response.jsonPath().getBoolean("booking.depositpaid"));
    }

    @Test(description = "Validate created booking response fields")
    public void verifyBookingResponseFields() {

        String requestBody = """
                {
                    "firstname":"Automation",
                    "lastname":"Tester",
                    "totalprice":1000,
                    "depositpaid":true,
                    "bookingdates":{
                        "checkin":"2026-02-01",
                        "checkout":"2026-02-05"
                    },
                    "additionalneeds":"Lunch"
                }
                """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post(BASE_URL + "/booking");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertNotNull(
                response.jsonPath().getString("bookingid"));

        Assert.assertEquals(
                response.jsonPath().getString("booking.firstname"),
                "Automation");

        Assert.assertEquals(
                response.jsonPath().getString("booking.lastname"),
                "Tester");

        Assert.assertEquals(
                response.jsonPath().getString(
                        "booking.additionalneeds"),
                "Lunch");
    }

    @Test(description = "Create booking and validate booking dates")
    public void verifyBookingDates() {

        String requestBody = """
                {
                    "firstname":"Date",
                    "lastname":"Validation",
                    "totalprice":750,
                    "depositpaid":true,
                    "bookingdates":{
                        "checkin":"2026-03-01",
                        "checkout":"2026-03-15"
                    },
                    "additionalneeds":"Dinner"
                }
                """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post(BASE_URL + "/booking");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(
                response.jsonPath()
                        .getString("booking.bookingdates.checkin"),
                "2026-03-01");

        Assert.assertEquals(
                response.jsonPath()
                        .getString("booking.bookingdates.checkout"),
                "2026-03-15");
    }

    @Test(description = "Create booking and validate additional needs")
    public void verifyAdditionalNeedsField() {

        String requestBody = """
                {
                    "firstname":"API",
                    "lastname":"User",
                    "totalprice":300,
                    "depositpaid":false,
                    "bookingdates":{
                        "checkin":"2026-04-01",
                        "checkout":"2026-04-05"
                    },
                    "additionalneeds":"Airport Pickup"
                }
                """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post(BASE_URL + "/booking");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(
                response.jsonPath()
                        .getString("booking.additionalneeds"),
                "Airport Pickup");
    }
}