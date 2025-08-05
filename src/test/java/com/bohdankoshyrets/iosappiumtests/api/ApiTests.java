package com.bohdankoshyrets.iosappiumtests.api;

import groovy.transform.Final;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.HashMap;
import java.util.Map;

public class ApiTests {
    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        System.out.println("SETUP API TESTS");
    }

    @Test
    public void testApi() {
        io.restassured.RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testVeryBigApi() {
        io.restassured.RestAssured.given()
                .contentType(ContentType.JSON)
                .get("/posts")
                .then()
                .statusCode(200)
                .body("", hasSize(100));

    }

    @Test
    public void currencyTest() {
        io.restassured.RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://rates.fm/api/latest.json")
                .then()
                .statusCode(206)
                .body("base", equalTo("UAH"))
                .body("rates.oktava.USD", hasEntry("currency", "USD"));
    }
}

