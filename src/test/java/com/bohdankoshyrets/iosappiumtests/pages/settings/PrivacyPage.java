package com.bohdankoshyrets.iosappiumtests.pages.settings;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class PrivacyPage {
    private IOSDriver driver;
    private WebDriverWait wait;

    private static final By LOCATION_SERVICES_CELL = AppiumBy.iOSClassChain("**/XCUIElementTypeCell/**/XCUIElementTypeButton[`name == 'LOCATION'`]");
    private WebElement locationServicesCell() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(LOCATION_SERVICES_CELL)
        );
    }

    @iOSXCUITFindBy(accessibility = "USER_TRACKING")
    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement userTrackingCell;

    public PrivacyPage(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);

    }

    public void assertPageIsVisible() {
        locationServicesCell().isDisplayed();
    }

    public void openUserTracking() {
        userTrackingCell.click();
    }
}