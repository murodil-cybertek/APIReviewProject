package com.cydeo.tests.day05_put_token_request;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.Request;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

public class LibraryWithTokenTest {
    @BeforeClass
    public static void setUp() {
        baseURI = "https://library2.cybertekschool.com/rest/v1";
    }

    /**
     1) GENERATE ACCESS TOKEN: send post request to /login endpoint with username and password
     2) READ and ASSIGN the token to a String variable
     3) SEND GET request to /get_book_list_for_borrowing url
     4) Verify status code is 200 and can see json body
     */

    @Test
    public void readAllBooks() {
        String accessToken = getAccessToken("student30@library", "Sdet2022*");
        System.out.println("accessToken = " + accessToken);

        Response response = given().header("x-library-token", accessToken)
                .when().get("/get_book_list_for_borrowing");
        System.out.println(response.statusCode());
        response.prettyPrint();

    }

    public static String getAccessToken(String email, String password) {
        Response response = given().formParams("email", email, "password", password)
                .when().post("/login");
        return response.jsonPath().getString("token");
    }

}
