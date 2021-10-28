package com.cydeo.tests.day03_json_body_verification;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class SpartansApiTest {
    @BeforeClass //runs once before any test
    public static void setUp() {
        RestAssured.baseURI = "http://52.207.61.129:8000/api";
    }

    @Test
    public void readAllSpartans() {
        Response response = given().accept("application/json").when().get("/spartans");
       // response.prettyPrint();
        //System.out.println(response.body().asString());
        //print data of first spartan:
        /*
         {
                "id": 2,
                "name": "Enum",
                "gender": "Female",
                "phone": 1234567891923
           }
         */
        JsonPath json = response.jsonPath();
        System.out.println("First Spartan data: ");
        System.out.println("id = " + json.getString("[0].id"));
        System.out.println("name = " + json.getString("[0].name"));
        System.out.println("gender = " + json.getString("[0].gender"));
        System.out.println("phone = " + json.getString("[0].phone"));

        System.out.println("Request date = " + response.header("date"));

    }

    /**
     DESERIALIZATION: Converting from Json to Java object
     Serialization : Converting from Java object to Json
     */
    @Test
    public void singleSpartanTest() {
        Response response = given().accept("application/json").when().get("/spartans/9");
        response.prettyPrint();
        Map<String, Object> spartanMap = response.body().as(Map.class);
        System.out.println(spartanMap);
        System.out.println("id = " + spartanMap.get("id"));
        System.out.println("name = " + spartanMap.get("name"));
        System.out.println("gender = " + spartanMap.get("gender"));
        System.out.println("phone = " + spartanMap.get("phone"));
    }

    /**
     DESERIALIZATION: Converting from Json to Java object/POJO
     */
    @Test
    public void singleSpartanPOJOTest() {
        Response response = given().accept("application/json").when().get("/spartans/9");
        response.prettyPrint();
        Spartan spartan = response.body().as(Spartan.class);
        System.out.println(spartan.id);
        System.out.println(spartan.name);
        System.out.println(spartan.gender);
        System.out.println(spartan.phone);

    }




}
