package com.bohdankoshyrets.iosappiumtests.api;

import com.bohdankoshyrets.iosappiumtests.api.utils.RatesApiClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RatesApiTests {
    Map<String, Map<String, Object>> rates;

    @DataProvider(name = "exchangeCurrencies")
    public Object[][] exchangeCurrencies() {
        return new Object[][] {
                { "goverla",    "USD" },
                { "goverla",    "EUR" },
                { "goverla",    "GBP" },
                { "cent",       "USD" },
                { "cent",       "EUR" },
                { "cent",       "GBP" },
                { "money24",    "USD" },
                { "money24",    "EUR" },
                { "money24",    "GBP" },
        };
    }

    @DataProvider(name = "currenciesAmount")
    public Object[][] currenciesAmount() {
        return new Object[][] {
                { "goverla", 9 },
                { "money24-7", 12 },
                { "kut-group", 8 },
                { "cashalot", 8 },
                { "centro-finance", 4 }
        };
    }

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = "https://rates.fm";
        rates = RatesApiClient.getAllRates();
        System.out.println("SETUP RATES API TESTS: " + rates);
    }

    @Test(dataProvider = "currenciesAmount")
    public void testCurrenciesAmount(String exchange, int amount) {
        assertThat("Currencies amount for " + exchange + " are correct", rates.get(exchange).size(), equalTo(amount));
    }

    @Test(dataProvider = "exchangeCurrencies" )
    public void testExchangeRatesStructure(String exchange, String currency) {
        assertThat("Rates are present", rates.size(), notNullValue());
        assertThat(exchange + " is present", rates.containsKey(exchange), equalTo(true));

        Map<String, Object> exchangeRates = rates.get(exchange);
        assertThat(exchange + " rates are not null", exchangeRates, notNullValue());

        assertThat(exchange + " has " + currency, exchangeRates.get(currency), notNullValue());

        @SuppressWarnings("unchecked")
        Map<String, Object> exchangeCurrencyRates = (Map<String, Object>) exchangeRates.get(currency);

        assertThat(exchange + currency + " currency set properly", exchangeCurrencyRates.get("currency"), equalTo(currency));
        assertThat(exchange + currency + " bid is a number", exchangeCurrencyRates.get("bid"), is(instanceOf(Number.class)));
        assertThat(exchange + currency + " ask is an number", exchangeCurrencyRates.get("ask"), is(instanceOf(Number.class)));

        assertThat(exchange + currency + " diff bid is a number", exchangeCurrencyRates.get("diff_bid"), is(instanceOf(Number.class)));
        assertThat(exchange + currency + " diff ask is an number", exchangeCurrencyRates.get("diff_ask"), is(instanceOf(Number.class)));
    }

    @Test(dataProvider = "exchangeCurrencies")
    public void testRequiredFieldsArePresent(String exchange, String currency) {
        Map<String, Object> exchanger = (Map<String, Object>) rates.get(exchange);
        Map<String, Object> currentCurrency = (Map<String, Object>) exchanger.get(currency);

        assertThat(exchange + " " + currency + " has all required fields", currentCurrency,
                allOf(
                        hasKey("currency"),
                        hasKey("bid"),
                        hasKey("ask"),
                        hasKey("diff_bid"),
                        hasKey("diff_ask"),
                        hasKey("timestamp")
                ));
    }
}