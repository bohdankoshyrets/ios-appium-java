package com.bohdankoshyrets.iosappiumtests.config;

import org.openqa.selenium.remote.DesiredCapabilities;

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
//        caps.setCapability("appium:autoLaunch", "false");
//        caps.setCapability("appium:noReset", "true");
        return caps;
    }
}
