package org.rest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in"; // Can also use Config.getBaseUrl()
    }
}