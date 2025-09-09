package com.bohdankoshyrets.iosappiumtests.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    final private static Properties PROPS = new Properties();

    static {
        try (InputStream input = TestConfig.class.getClassLoader().getResourceAsStream("appium.properties")) {
            if (input != null) {
                PROPS.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("cannot load appium.properties", e);
        }
    }

    public static String get(String key) {
        return System.getProperty(key, PROPS.getProperty(key));
    }
}