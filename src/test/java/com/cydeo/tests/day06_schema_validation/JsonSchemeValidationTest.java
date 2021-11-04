package com.cydeo.tests.day06_schema_validation;

import com.cydeo.tests.utils.TokenGenerator;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class JsonSchemeValidationTest extends LibraryApiTestBase{
    @Test
    public void getUserByIdSchemaValidation() {
        String accessToken = TokenGenerator.getAccessToken("librarian46@library",
                "Sdet2022*");
        given().header("x-library-token", accessToken)
                .when().get("/get_user_by_id/1")
                .then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("UserJsonSchema.json")));
    }
}
