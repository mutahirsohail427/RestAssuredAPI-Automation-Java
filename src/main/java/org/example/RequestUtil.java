package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class RequestUtil {

    public static Response get(String endpoint) {
//        return RestAssured.get(endpoint);
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("page", 2)
                .get(endpoint);
    }

    public static Response post(String endpoint, Object body) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post(endpoint);
    }

    public static Response put(String endpoint, Object body) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .put(endpoint);
    }

    public static Response delete(String endpoint) {
        return RestAssured.delete(endpoint);
    }
}
