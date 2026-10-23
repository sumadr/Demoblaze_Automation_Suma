package apitest.clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingAPI {
    public Response createBooking(String body) {
        return given().contentType("application/json").body(body).post("/booking");
    }

    public Response getBooking(int id) {
        return given().get("/booking/" + id);
    }

    public Response deleteBooking(int id, String token) {
        return given().cookie("token", token).delete("/booking/" + id);
    }
}