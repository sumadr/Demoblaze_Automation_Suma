package apitest.base;

import io.restassured.RestAssured;

public class BaseApiTest {

    static {

        RestAssured.baseURI =
                "https://restful-booker.herokuapp.com";
    }
}