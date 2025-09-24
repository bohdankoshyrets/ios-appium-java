package com.bohdankoshyrets.iosappiumtests.api;

import com.bohdankoshyrets.iosappiumtests.api.utils.RatesApiClient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Instant;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class P0RatesTests extends RatesApiClient {
    private static final String ENDPOINT = "/api/latest.json";
    private static volatile Response cachedResponse;
    private static final int STATUS_CODE_OK = 200;
    private static final int STATUS_CODE_PARTIAL = 206;
    private static final String BANK_NBU = "nbu";

    @BeforeClass
    public void cacheResponse() {
        if (cachedResponse == null) {
            synchronized (P0RatesTests.class) {
                if (cachedResponse == null) {
                    cachedResponse = given().spec(spec())
                            .when().get(ENDPOINT);
                }
            }
        }
    }

    @AfterClass
    public void clearResponse() {
        cachedResponse = null;
    }

    @Test
    public void contract_and_top_level_fields_are_present() {
        cachedResponse
                .then().statusCode(anyOf(is(STATUS_CODE_OK), is(STATUS_CODE_PARTIAL)))
                .body(matchesJsonSchemaInClasspath("schemas/rates_page.schema.json"));
    }

    @Test
    public void content_type_is_json() {
        cachedResponse
                .then().contentType(ContentType.JSON);
    }

    @Test
    public void base_exists_and_its_uah() {
        cachedResponse
                .then().body("base", notNullValue())
                .body("base", equalTo("UAH"));
    }

    @Test
    public void rates_are_valid_for_nbu() {
        Map <String, Map<String, Object>> rates = fetchAllPagesAndMerge();
        SoftAssert sa = new SoftAssert();
        Assert.assertTrue(rates.containsKey(BANK_NBU), "NBU should be present in rates");
        Assert.assertFalse(rates.get(BANK_NBU).isEmpty(), "NBU should have at least one currency");

        rates.get(BANK_NBU).forEach((k, v) -> {
            @SuppressWarnings("unchecked")
            Map<String, Object> ratesMap = (Map<String, Object>) v;
            String currency = (String) ratesMap.get("currency");
            float bid = ((Number) ratesMap.get("bid")).floatValue();
            float ask = ((Number) ratesMap.get("ask")).floatValue();
            float diffBid = ((Number) ratesMap.get("diff_bid")).floatValue();
            float diffAsk = ((Number) ratesMap.get("diff_ask")).floatValue();

            sa.assertTrue(bid > 0, "NBU: Bid should be positive for " + currency);
            sa.assertTrue(ask > 0, "NBU: Ask should be positive for " + currency);

            sa.assertEquals(bid, ask, "NBU: Bid and ask should be equal for " + currency);
            sa.assertEquals(k, currency, "NBU: Currency should be equal for " + currency);
            sa.assertEquals(diffAsk, diffBid, "NBU: Diff ask and diff bid should be equal for " + currency);
        });
        sa.assertAll();
    }

    @Test
    public void rates_are_valid() {
        Map<String, Map<String, Object>> rates = fetchAllPagesAndMerge();
        SoftAssert sa = new SoftAssert();

        rates.forEach((bank_key, bank) -> bank.forEach((currency_key, v) -> {
            @SuppressWarnings("unchecked")
            Map<String, Object> ratesMap = (Map<String, Object>) v;
            float bid = ((Number) ratesMap.get("bid")).floatValue();
            float ask = ((Number) ratesMap.get("ask")).floatValue();
            Instant ts = Instant.parse((String) ratesMap.get("timestamp"));

            sa.assertTrue(ts.getNano() <= Instant.now().getNano(), bank_key + ": " + "Timestamp should not be in future " + currency_key);
            sa.assertTrue(ask >= bid, bank_key + ": " + "Ask " + ask + " should be >= bid "+ bid +" for " + currency_key);
        }));
        sa.assertAll();
    }
}