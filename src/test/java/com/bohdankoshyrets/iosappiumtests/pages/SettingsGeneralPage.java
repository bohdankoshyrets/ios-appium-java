package com.bohdankoshyrets.iosappiumtests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SettingsGeneralPage extends BasePage {
    private static final By ABOUT_CELL = AppiumBy.accessibilityId("About");
    private static final By KEYBOARD_CELL = AppiumBy.accessibilityId("Keyboard");
    private static final By NAV_BAR_TITLE = AppiumBy.iOSClassChain("**/XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[`label == 'General'`]");
    private static final By GENERAL_DESCRIPTION_TITLE = AppiumBy.iOSClassChain("**/XCUIElementTypeCell/**/XCUIElementTypeStaticText[`label == 'General'`]");
    private static final By GENERAL_DESCRIPTION_TEXT = AppiumBy.iOSClassChain("**/XCUIElementTypeCell/**/XCUIElementTypeStaticText[`label BEGINSWITH 'Manage your overall setup and preferences'`]");

    public SettingsGeneralPage(IOSDriver driver) {
        super(driver);
    }

    private WebElement aboutCell() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(ABOUT_CELL)
        );
    }

    private WebElement keyboardCell() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(KEYBOARD_CELL)
        );
    }

    public void assertGeneralDescriptionIsVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(GENERAL_DESCRIPTION_TITLE));
        wait.until(ExpectedConditions.visibilityOfElementLocated(GENERAL_DESCRIPTION_TEXT));
    }

    public void assertPageIsVisible() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(NAV_BAR_TITLE)
        );
    }

    public void openAbout() {
        aboutCell().click();
    }

    public void openKeyboard() {
        keyboardCell().click();
    }
}