package com.bohdankoshyrets.iosappiumtests.config;

import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;

public class AppiumCapabilities {
    private static final String PLATFORM_NAME = "iOS";
    private static final String PLATFORM_VERSION = "18.6";
    private static final String DEVICE_NAME = "iPhone 16";
    private static final String AUTOMATION_NAME = "XCUITest";

    public static DesiredCapabilities getCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", PLATFORM_NAME);
        caps.setCapability("appium:platformVersion", PLATFORM_VERSION);
        caps.setCapability("appium:deviceName", DEVICE_NAME);
        caps.setCapability("appium:automationName", AUTOMATION_NAME);
        caps.setCapability("appium:bundleId", BundleID.preferences);
        return caps;
    }

    public static XCUITestOptions getOptions() {
        String udid = System.getProperty("appium.device.udid");
        XCUITestOptions opts = new XCUITestOptions();

        opts.setPlatformName(PLATFORM_NAME);
        opts.setWdaLaunchTimeout(Duration.ofSeconds(30));
        opts.setShowXcodeLog(true);
        opts.setBundleId(BundleID.preferences);
        opts.setCapability("appium:platformVersion", PLATFORM_VERSION);
        opts.setCapability("appium:deviceName", DEVICE_NAME);

        if (udid != null) {
            opts.setUdid(udid);
        }

        return opts;
    }
}
