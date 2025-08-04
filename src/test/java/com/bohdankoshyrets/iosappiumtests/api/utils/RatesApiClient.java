package com.bohdankoshyrets.iosappiumtests.api.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;

public class RatesApiClient {
    public static Map<String, Map<String, Object>> getAllRates() {
        String nextPage = "https://rates.fm/api/latest.json";
        Map<String, Map<String, Object>> rates = new HashMap<>();
        int pageCount = 0;
        final int MAX_PAGES = 10;

        while (nextPage != null && pageCount < MAX_PAGES) {
            pageCount++;
            System.out.println("Processing page " + pageCount);

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .get(nextPage)
                    .then()
                    .statusCode(206)
                    .body("rates", notNullValue())
                    .extract().response();

            nextPage = response.jsonPath().getString("paging.next");

            Map<String, Map<String, Object>> ratesMap = response.jsonPath().getMap("rates");

            for (String key : ratesMap.keySet()) {
                if (rates.containsKey(key)) {
                    System.out.println("Key: " + key + " already exists");
                    Map<String, Object> cantorExistingMap = rates.get(key);
                    Map<String, Object> cantorMap = ratesMap.get(key);
                    cantorExistingMap.putAll(cantorMap);
                    rates.put(key, cantorExistingMap);
                } else {
                    rates.put(key, ratesMap.get(key));
                }
            }
        }
        return rates;
    }
}