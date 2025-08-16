package com.bohdankoshyrets.iosappiumtests.config;

import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;
import java.util.Arrays;

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
        caps.setCapability("appium:setUdid", "C6BD72C6-D90F-4F57-83D4-18725E2D3141");
//        caps.setCapability("appium:autoLaunch", "false");
//        caps.setCapability("appium:noReset", "true");
        return caps;
    }

    public static XCUITestOptions getOptions() {
        System.out.println("UDID: " + System.getProperty("appium.device.udid"));
        XCUITestOptions opts = new XCUITestOptions();
        opts.setUdid(System.getProperty("appium.device.udid"));
        opts.setPlatformName(PLATFORM_NAME);
        opts.setPlatformVersion("18.6");
        opts.setWdaLaunchTimeout(Duration.ofSeconds(60));
        opts.setShowXcodeLog(true);
        opts.setCapability("xcodebuildArgs", Arrays.asList(
                "-sdk", "iphonesimulator"
        ));

        return opts;
    }
}
