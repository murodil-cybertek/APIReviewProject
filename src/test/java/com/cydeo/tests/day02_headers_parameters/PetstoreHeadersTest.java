package com.cydeo.tests.day02_headers_parameters;

import io.restassured.response.Response;
import org.junit.Test;
//static import of RestAssured
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class PetstoreHeadersTest {

    /**
     Given Accept type is Application Json
     When I send get request to https://petstore.swagger.io/v2/store/inventory
     Then status code should be 200
     And Content type should be application json
     */
    String baseUrl = "https://petstore.swagger.io/v2/store";
    @Test
    public void inventoryHeadersTest() {
        given().accept("application/json").
                when().get(baseUrl + "/inventory").
                then().statusCode(200).
                and().contentType("application/json");

        //same test with using Response object
        Response response = given().accept("application/json").when().get(baseUrl+"/inventory");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
    }

    /**
     * NEGATIVE TEST
     Given Accept type is Application json
     When i send get request to https://petstore.swagger.io/v2/store/order/3000
     Then status code is 404
     And Content type is "Application json"
     */
    @Test
    public void negativeOrderTest() {
        given().headers("Accept","application/json").
                when().get(baseUrl + "/order/30000").
                then().statusCode(404).
                and().contentType("application/json");
    }

}
