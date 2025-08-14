package com.bohdankoshyrets.iosappiumtests.pages.settings.userTracking;

import com.bohdankoshyrets.iosappiumtests.pages.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserTrackingPage extends BasePage {
    private static final String SCREEN_TITLE = "Tracking";
    private static final By NAVBAR_TITLE_LOCATOR = AppiumBy.iOSClassChain(String.format("**/XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[`label == '%s'`]", SCREEN_TITLE));

    public UserTrackingPage(IOSDriver driver) {
        super(driver);
    }

    public void assertPageIsVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(NAVBAR_TITLE_LOCATOR));
    }
}