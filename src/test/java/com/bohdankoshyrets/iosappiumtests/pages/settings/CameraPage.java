package com.bohdankoshyrets.iosappiumtests.pages.settings;

import com.bohdankoshyrets.iosappiumtests.pages.BasePage;
import com.bohdankoshyrets.iosappiumtests.pages.enums.CameraOptionItem;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SwitchState;


import java.time.Duration;

import static com.bohdankoshyrets.iosappiumtests.pages.enums.CameraOptionItem.*;

public class CameraPage extends BasePage {
    private final SoftAssert softAssert;

    private static final String SCREEN_TITLE = "Camera";
    private static final By NAVBAR_TITLE_LOCATOR = AppiumBy.iOSClassChain(String.format("**/XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[`label == '%s'`]", SCREEN_TITLE));

    public CameraPage(IOSDriver driver) {
        super(driver);
        this.softAssert = new SoftAssert();
    }

    public void assertPageIsShown() {
        wait.until(ExpectedConditions.elementToBeClickable(NAVBAR_TITLE_LOCATOR));
        wait.until(ExpectedConditions.elementToBeClickable(PHOTOGRAPHIC_STYLES_CELL.getBy()));
        wait.until(ExpectedConditions.elementToBeClickable(RECORD_VIDEO_CELL.getBy()));
        wait.until(ExpectedConditions.elementToBeClickable(RECORD_SLO_MO_CELL.getBy()));
        wait.until(ExpectedConditions.elementToBeClickable(RECORD_SOUND_CELL.getBy()));
    }

    public void assertDefaultValues() {
        softAssertCellValue(PHOTOGRAPHIC_STYLES_CELL, "Standard");
        softAssertCellValue(RECORD_VIDEO_CELL,  "1080p at 30 fps");
        softAssertCellValue(RECORD_SLO_MO_CELL, "1080p at 240 fps");
        softAssertCellValue(RECORD_SOUND_CELL, "Spatial Audio");

        softAssert.assertAll();
    }

    private void softAssertCellValue(CameraOptionItem locator, String expected) {
        WebElement cell = driver.findElement(locator.getBy());
        softAssert.assertEquals(cell.getAttribute("value"), expected,
                String.format("value of the cell '%s' should be equal to '%s', but it's '%s' instead.",
                        cell.getAttribute("name"),
                        expected,
                        cell.getAttribute("value"))
        );
    }

    public void toggleCellSwitch(SwitchState state, CameraOptionItem optionItem) {
        WebElement cell = driver.findElement(optionItem.getBy());
        // to scroll down to the cell without changing the switch state
        cell.findElement(AppiumBy.iOSClassChain("XCUIElementTypeStaticText")).click();
        String cellValue = cell.getAttribute("value");
        String name = cell.getAttribute("name");
        System.out.printf("cell value for %s: %s%n", name, cellValue);

        SwitchState currentSwitchState = SwitchState.fromString(cellValue);

        if (!currentSwitchState.equals(state)) {
            cell.click();
        }

        Assert.assertEquals(cell.getAttribute("value"), state.getStringValue());

    }
}