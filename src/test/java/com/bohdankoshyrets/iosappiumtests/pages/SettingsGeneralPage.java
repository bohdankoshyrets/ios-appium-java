package com.bohdankoshyrets.iosappiumtests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SettingsGeneralPage {
    private final IOSDriver driver;
    private final WebDriverWait wait;
    private static final By ABOUT_CELL = AppiumBy.accessibilityId("About");

    public SettingsGeneralPage(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement aboutCell() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(ABOUT_CELL)
        );
    }

    public void openAbout() {
        aboutCell().click();
    }
}