package com.bohdankoshyrets.iosappiumtests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class KeyboardPage extends BasePage {

    private static final By NAV_BAR_TITLE = AppiumBy.iOSClassChain("**/XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[`label == 'Keyboards'`]");
    private static final By KEYBOARD_CELL = AppiumBy.iOSClassChain("**/XCUIElementTypeCell[`name == 'Keyboards'`]");

    public KeyboardPage(IOSDriver driver) {
        super(driver);
    }

    public void assertPageIsVisible() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(NAV_BAR_TITLE)
        );
    }

    public void openKeyboards() {
        WebElement keyboardsCell = wait.until(
                ExpectedConditions.elementToBeClickable(KEYBOARD_CELL)
        );
        keyboardsCell.click();
    }
}