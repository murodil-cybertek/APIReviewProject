package com.cydeo.tests.day05_put_token_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

public class SpartanUpdateTest {
    @BeforeClass
    public static void setUp() {
        baseURI = "http://54.205.239.177:8000";
    }

    @Test
    public void updateExistingSpartanPUTRequest() {
        Map<String,Object> jsonMap = new HashMap<>();
        //jsonMap.put("name", "Maria");
        //jsonMap.put("gender", "Female");
        jsonMap.put("phone", 2022348596L);

        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonMap)
                .when().patch("/api/spartans/12");
        System.out.println("status code = " + response.statusCode());
        response.body().prettyPrint();
        System.out.println("headers = " + response.getHeaders().toString());
        assertEquals(204, response.statusCode());
    }

    @Test
    public void deleteSpartan() {
        when().delete("/api/spartans/17").then().statusCode(204);
    }
}
