package org.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.APIEndpoints;
import org.example.RequestUtil;
import org.example.userData;
import org.example.userResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ApiTests extends BaseTest {

    @Test(priority = 1)
    public void testGetUsers() throws JsonProcessingException {
        System.out.println("Executing GET endpoint");
        Response response = RequestUtil.get(APIEndpoints.USERS);
        Assert.assertEquals(response.getStatusCode(), 200);
        String jsonString = response.getBody().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        userResponse userResponse = objectMapper.readValue(jsonString, userResponse.class);
        System.out.println("Page: " + userResponse.getPage());
        for (userData user : userResponse.getData()) {
            System.out.println("User Name: " + user.getFirst_name() + " " + user.getLast_name());
        }
        System.out.println(userResponse.getSupport().getText());
        System.out.println(userResponse.getSupport().getUrl());
    }

    @Test(priority = 2)
    public void testCreateUser() throws IOException {
        System.out.println("Executing POST endpoint");
        String result = readFile("createUser.json");
        System.out.println(result);
        Response response = RequestUtil.post(APIEndpoints.USERS, result);
        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 201);

    }

    @Test(priority = 3)
    public void testDeleteUsers() throws JsonProcessingException {
        System.out.println("Executing DELETE endpoint");
        Response response = RequestUtil.delete(APIEndpoints.DELETE);
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    public String readFile(String fileName){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        String result = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }
}