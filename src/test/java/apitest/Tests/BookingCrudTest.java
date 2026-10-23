package apitest.Tests;

import apitest.clients.AuthAPI;
import apitest.clients.BookingAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingCrudTest {

    @Test
    public void verifyBookingCrudFlow() {

        AuthAPI auth = new AuthAPI();
        BookingAPI booking = new BookingAPI();

        String token = auth.generateToken();

        String payload = """
                {
                  "firstname":"John",
                  "lastname":"Smith",
                  "totalprice":100,
                  "depositpaid":true,
                  "bookingdates":{
                      "checkin":"2026-01-01",
                      "checkout":"2026-01-10"
                  },
                  "additionalneeds":"Breakfast"
                }
                """;

        Response response =
                booking.createBooking(payload);

        int bookingId =
                response.jsonPath().getInt("bookingid");

        Assert.assertTrue(bookingId > 0);

        Response getResponse =
                booking.getBooking(bookingId);

        Assert.assertEquals(
                getResponse.statusCode(),
                200);

        Response deleteResponse =
                booking.deleteBooking(
                        bookingId,
                        token);

        Assert.assertEquals(
                deleteResponse.statusCode(),
                201);
    }
}