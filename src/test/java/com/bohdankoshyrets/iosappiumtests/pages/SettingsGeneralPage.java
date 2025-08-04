package com.bohdankoshyrets.iosappiumtests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

public class SettingsGeneralPage {
    private final IOSDriver driver;

    public SettingsGeneralPage(IOSDriver driver) {
        this.driver = driver;
    }

    private WebElement aboutCell() {
        return driver.findElement(AppiumBy.accessibilityId("About"));
    }

    public void openAbout() {
        aboutCell().click();
    }
}