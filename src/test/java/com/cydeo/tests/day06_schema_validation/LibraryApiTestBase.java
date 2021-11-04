package com.cydeo.tests.day06_schema_validation;

import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;

public class LibraryApiTestBase {

    @BeforeClass
    public static void setUp() {
        baseURI = "https://library2.cybertekschool.com/rest/v1";
    }

}
