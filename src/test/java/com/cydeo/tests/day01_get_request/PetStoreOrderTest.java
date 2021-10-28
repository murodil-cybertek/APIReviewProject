package com.cydeo.tests.day01_get_request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class PetStoreOrderTest {
    String baseUrl = "https://petstore.swagger.io/v2/store";

    @Test
    public void inventoryTest() {
        Response response = RestAssured.get(baseUrl+"/inventory");
        System.out.println("status code = " + response.statusCode());
        System.out.println("json body = " + response.asPrettyString());
    }

    /**
     * Send get request to /order/3
     * Verify response status code is 200
     */
    @Test
    public void orderIdUrlParamTest() {
        // RESPONSE PART              GET REQUEST PART
        Response response = RestAssured.get(baseUrl+"/order/3");
        System.out.println("status code = " + response.statusCode());
        response.prettyPrint();
        Assert.assertEquals(200, response.statusCode());

        //JDBC Select
    }
}
