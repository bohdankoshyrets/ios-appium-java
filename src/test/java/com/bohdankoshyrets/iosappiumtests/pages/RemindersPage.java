package com.bohdankoshyrets.iosappiumtests.pages;

import com.bohdankoshyrets.iosappiumtests.base.BasePage;
import com.bohdankoshyrets.iosappiumtests.config.BundleID;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RemindersPage extends BasePage {
    private static final String SCREEN_TITLE = "Reminders";
    private static final By REMINDERS_LIST = AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"RemindersList.ID.RemindersTable\"`]");
    private static final By SPLASH_SCREEN_TITLE = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"Welcome to Reminders\"`]");
    private static final By SPLASH_SCREEN_CONTINUE_BUTTON = AppiumBy.accessibilityId("Continue");

    public RemindersPage(IOSDriver driver) {
        super(driver);
    }

    public void launchApp() {
        driver.activateApp(BundleID.reminders);
    }

    // TODO:
    public void dismissTutorialIfPresent() {

    }

    public void assertPageIsShown() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(REMINDERS_LIST)
        );
    }
}