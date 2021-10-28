package com.cydeo.tests.day03_json_body_verification;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

public class TypiCodeTask {

    String url = "https://jsonplaceholder.typicode.com/users/1";

    @Test
    public void userDataTest() {
        Response response = given().accept("application/json").when().get(url);
        assertEquals(200, response.statusCode());
        System.out.println("status code = " + response.statusCode());
        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("email = " + response.path("email"));

        assertEquals("Leanne Graham" , response.path("name"));
        assertEquals("Gwenborough", response.path("address.city"));

        System.out.println("city = " + response.path("address.city"));
        System.out.println("zip code = " + response.path("address.zipcode"));

        //response.prettyPrint();
        //print company name
        String company = response.path("company.name");
        System.out.println("company = " + company);

        //assert latitude is -37.3159
        String lat = response.path("address.geo.lat");
        System.out.println("lat = " + lat);
        String expLat = "-37.3159";
        assertEquals(expLat , lat);

    }

    @Test
    public void userDataJsonPathTest() {
        Response response = get(url);
        JsonPath json = response.jsonPath();
        System.out.println("name = " + json.getString("name"));
        assertEquals("Leanne Graham" , json.getString("name"));
        int id = json.getInt("id");
        int id2 = response.path("id");

        System.out.println("id = " + id);
        System.out.println("id2 = " + id2);

        assertEquals("Gwenborough", json.getString("address.city"));
    }

    @Test
    public void usersListTest() {
        Response response = get("https://jsonplaceholder.typicode.com/users");
        //assign response to json object
        JsonPath json = response.jsonPath();
        //we use index [0] when working with json arrays/lists
        System.out.println(json.getString("[1]")); //prints whole json for index 1
        //print first users name
        System.out.println(json.getString("[0].name"));
        //print second users city
        System.out.println(json.getString("[1].address.city"));

        //store all emails in to List<String>
        List<String> emails = json.getList("email"); //read all emails for each user
        System.out.println(emails.size());
        for(String email : emails) {
            System.out.println("email = " + email);
        }

        //assert that each 10 user has an email. count should be 10
        assertEquals(10, emails.size());
        //assert that Sherwood@rosamond.me is among emails
        assertTrue(emails.contains("Sherwood@rosamond.me"));

        //add all cities into List<String> then print out in same line




    }

}
