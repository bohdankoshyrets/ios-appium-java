package com.bohdankoshyrets.iosappiumtests.config;

import io.appium.java_client.ios.options.XCUITestOptions;
import java.time.Duration;

public class AppiumCapabilities {
    public static XCUITestOptions getOptions() {
        String udid = System.getProperty("appium.device.udid");
        XCUITestOptions opts = new XCUITestOptions();

        opts.setCapability("appium:platformVersion", TestConfig.get("appium.platformVersion"));
        opts.setCapability("appium:deviceName", TestConfig.get("appium.deviceName"));
        opts.setPlatformName(TestConfig.get("appium.platformName"));
        opts.setWdaLaunchTimeout(Duration.ofSeconds(180));
        opts.setShowXcodeLog(false);

        if (udid != null) {
            opts.setUdid(udid);
        }

        return opts;
    }
}
