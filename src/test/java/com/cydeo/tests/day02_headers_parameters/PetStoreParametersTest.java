package com.cydeo.tests.day02_headers_parameters;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class PetStoreParametersTest {

   String baseUrl = "https://petstore.swagger.io/v2";

        /**
         Given accept type is application/json
         And query params status is available
         When i send get request to https://petstore.swagger.io/v2/pet/findByStatus
         Then status code is 200
         And Content type is application/json
         */

    @Test
    public void findPetByStatusQueryParamTest() {
        given().accept("application/json").
                and().queryParam("status","available").
                when().get(baseUrl + "/pet/findByStatus").
                then().statusCode(200).
                and().contentType("application/json");

    }

    @Test
    public void findPetByStatusQueryParamTest2() {
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("status","available");

        Response response = given().accept("application/json").
                           and().queryParams(paramsMap).
                            when().get(baseUrl + "/pet/findByStatus");
        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
    }
    /**
     Given accept type is application/json
     And query param status is java
     When i send get request to https://petstore.swagger.io/v2/pet/findByStatus
     Then status code is 200
     And Content type is application/json
     And body should be empty
     */
    @Test
    public void findByStatusNegativeTest() {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("status", "java");

        Response response = given().accept("application/json").
                and().params(paramsMap).
                when().get(baseUrl+"/pet/findByStatus");

        response.prettyPrint();
        assertEquals(200, response.statusCode());
        //ensure there is no "id" string in response body
        assertFalse(response.toString().contains("id"));

        //assertNull(response.path("id").toString());

    }

}
