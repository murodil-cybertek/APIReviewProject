package com.cydeo.tests.day04_post_put_requests;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class SpartanPostRequest {
    @BeforeClass
    public static void setUp() {
        baseURI = "http://54.205.239.177:8000";
    }

    /**
     Given accept type and content type are json
     And request json body is :
     "{
     "name":"Murodil",
     "gender" : "Male",
     "phone" : 2023751774
     }"
     When i send post request
     Then status code is 201
     And content type is json
     And body contains message "success": "A Spartan is Born!"
     And id is auto generated
     And name, gender, phone info matches my request
     */
    @Test
    public void postNewSpartanTest() {
        String requestBody = "{\n" +
                "     \"name\":\"Murodil\",\n" +
                "     \"gender\" : \"Male\",\n" +
                "     \"phone\" : 2023751774\n" +
                "     }";
        Response response = given().accept("application/json").and()
                .contentType("application/json")
                .body(requestBody)
                .when().post("/api/spartans");
        System.out.println("Status code = " + response.statusCode());
        response.prettyPrint();
        assertEquals(201, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals("A Spartan is Born!", response.jsonPath().getString("success"));
    }

    /**
     SERIALIZATION -> process of converting from java object to JSON
     GSON library is helping with that
     HashMap object -> Json
     */
    @Test
    public void postNewSpartanTestSerialize() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name","Murodil");
        requestBody.put("gender","Male");
        requestBody.put("phone",2023751774L);

        Response response = given().accept("application/json").and()
                .contentType("application/json")
                .body(requestBody)
                .when().post("/api/spartans");
        System.out.println("Status code = " + response.statusCode());
        response.prettyPrint();
        assertEquals(201, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals("A Spartan is Born!", response.jsonPath().getString("success"));
    }

}
