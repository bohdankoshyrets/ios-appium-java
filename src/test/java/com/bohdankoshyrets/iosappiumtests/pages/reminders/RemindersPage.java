package com.bohdankoshyrets.iosappiumtests.pages.reminders;

import com.bohdankoshyrets.iosappiumtests.base.BasePage;
import com.bohdankoshyrets.iosappiumtests.config.BundleID;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class RemindersPage extends BasePage {
    private static final String SCREEN_TITLE = "Reminders";
//    private static final By REMINDERS_LIST = AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"RemindersList.ID.RemindersTable\"`]");
    private static final By SPLASH_SCREEN_TITLE = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"Welcome to Reminders\"`]");
    private static final By SPLASH_SCREEN_CONTINUE_BUTTON = AppiumBy.accessibilityId("Continue");
    private static final By ICLOUD_SYNC_ALERT = AppiumBy.iOSClassChain("**/XCUIElementTypeAlert[`name BEGINSWITH \"Enable iCloud Syncing\"`]");
    private static final By NOT_NOW_BUTTON = AppiumBy.accessibilityId("Not Now");
    private static final By ADD_LIST_TOOLBAR_BUTTON = AppiumBy.iOSClassChain("**/XCUIElementTypeToolbar/**/XCUIElementTypeButton[`name == \"Add List\"`]");
    private static final By DEFAULT_LIST_CELL = AppiumBy.iOSClassChain("**/XCUIElementTypeCell/XCUIElementTypeStaticText[`label == \"Reminders\"`]");


    public RemindersPage(IOSDriver driver) {
        super(driver);
    }

    public void launchApp() {
        driver.activateApp(BundleID.reminders);
    }

    public void terminateApp() {
        driver.terminateApp(BundleID.reminders);
    }

    public void dismissTutorialIfPresent() {
        if (!driver.findElements(SPLASH_SCREEN_TITLE).isEmpty()) {
            driver.findElement(SPLASH_SCREEN_CONTINUE_BUTTON).click();
        }
    }

    public void dismissICloudSyncIfPresent() {
        List<WebElement> alert = driver.findElements(ICLOUD_SYNC_ALERT);
        if (!alert.isEmpty()) {
            alert.get(0).findElement(NOT_NOW_BUTTON).click();
            // to return to the root screen
            driver.navigate().back();
        }
    }

    public void assertPageIsShown() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(ADD_LIST_TOOLBAR_BUTTON)
        );
    }

    public void openDefaultList() {
        driver.findElement(DEFAULT_LIST_CELL).click();
    }
}