package com.cydeo.tests.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenGenerator {
    public static String getAccessToken(String email, String password) {
        Response response = given().formParams("email", email, "password", password)
                .when().post("/login");
        return response.jsonPath().getString("token");
    }
}

