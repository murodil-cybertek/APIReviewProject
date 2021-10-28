package com.cydeo.tests.day03_json_body_verification;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

public class SpartansApiTest {
    @BeforeClass //runs once before any test
    public static void setUp() {
        RestAssured.baseURI = "http://52.207.61.129:8000/api";
    }

    @Test
    public void readAllSpartans() {
        Response response = given().accept("application/json").when().get("/spartans");
        response.prettyPrint();
    }

}
