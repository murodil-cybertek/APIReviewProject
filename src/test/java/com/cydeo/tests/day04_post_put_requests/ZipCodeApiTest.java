package com.cydeo.tests.day04_post_put_requests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class ZipCodeApiTest {

    /**
     Given the accept type is json
     When I send get request to "http://api.zippopotam.us/us/22102"
     Then status code should be 200
     and body place name should be "Mc Lean"
     */

    @BeforeClass
    public static void setUp() {
        baseURI = "http://api.zippopotam.us/us/";
    }

    @Test
    public void verifyCityByZipCodeTest() {
        //Send get request with valid path parameter zipcode and store in response object
        Response response = given().accept("application/json").
                when().get("22102");
        //print the response json body
        response.prettyPrint();
        //verifying status code
        assertEquals(200, response.statusCode());
        //verifying headers: content type and Date
        assertEquals("application/json", response.contentType());
        assertTrue(response.getHeader("Date").contains("Mon, 01 Nov 2021"));

        //Verify place name "Mc Lean" using path method
        assertEquals("Mc Lean", response.path("places[0].'place name'"));
        assertEquals("Virginia", response.path("places[0].state"));

        //verify same using jsonPath:
        JsonPath json = response.jsonPath();
        System.out.println("country = " + json.getString("country"));
        System.out.println("city = " + json.getString("places[0].'place name'"));
        System.out.println("state = " + json.getString("places[0].state"));

        assertEquals("Mc Lean", json.getString("places[0].'place name'"));
        assertEquals("Virginia", json.getString("places[0].state"));

        //verify city and state using de-serialization with Java collections
        //Json -> java object
        Map<String, Object> jsonMap = response.as(Map.class);
        System.out.println("country = " + jsonMap.get("country"));
        System.out.println("places = " + jsonMap.get("places"));

        Map<String, Object> placeInfo = json.getMap("places[0]");
        System.out.println("City = " + placeInfo.get("place name"));




    }

}
