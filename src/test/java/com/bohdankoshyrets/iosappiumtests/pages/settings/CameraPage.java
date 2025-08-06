package com.bohdankoshyrets.iosappiumtests.pages.settings;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SwitchState;


import java.time.Duration;

public class CameraPage {
    private final IOSDriver driver;
    private final WebDriverWait wait;
    private final SoftAssert softAssert;

    private static final String SCREEN_TITLE = "Camera";
    private static final By NAVBAR_TITLE_LOCATOR =      AppiumBy.iOSClassChain(String.format("**/XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[`label == '%s'`]", SCREEN_TITLE));
    private static final By PHOTOGRAPHIC_STYLES_CELL =  AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeCell' AND name == 'Photographic Styles'");
    private static final By RECORD_VIDEO_CELL =         AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeCell' AND name == 'Record Video'");
    private static final By RECORD_SLO_MO_CELL =        AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeCell' AND name == 'Record Slo-mo'");
    private static final By RECORD_SOUND_CELL =         AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeCell' AND name == 'Record Sound'");
    private static final By GRID_CELL =                 AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeCell' AND name == 'Grid'");
    private static final By LEVEL_CELL =                AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeCell' AND name == 'Level'");
    private static final By MIRROR_FRONT_CAM_CELL =     AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeCell' AND name == 'Mirror Front Camera'");
    private static final By VIEW_OUTSIDE_FRAME_CELL =   AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeCell' AND name == 'View Outside the Frame'");
    private static final By LENS_CORRECTION_CELL =      AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeCell' AND name == 'Lens Correction'");
    private static final By MACRO_CONTROL_CELL =        AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeCell' AND name == 'Macro Control'");

    public CameraPage(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.softAssert = new SoftAssert();
    }

    public CameraPage assertPageIsShown() {
        wait.until(ExpectedConditions.elementToBeClickable(NAVBAR_TITLE_LOCATOR));
        wait.until(ExpectedConditions.elementToBeClickable(PHOTOGRAPHIC_STYLES_CELL));
        wait.until(ExpectedConditions.elementToBeClickable(RECORD_VIDEO_CELL));
        wait.until(ExpectedConditions.elementToBeClickable(RECORD_SLO_MO_CELL));
        wait.until(ExpectedConditions.elementToBeClickable(RECORD_SOUND_CELL));
        return this;
    }

    public CameraPage assertDefaultValues() {
        softAssertCellValue(PHOTOGRAPHIC_STYLES_CELL, "Standard");
        softAssertCellValue(RECORD_VIDEO_CELL,  "1080p at 30 fps");
        softAssertCellValue(RECORD_SLO_MO_CELL, "1080p at 240 fps");
        softAssertCellValue(RECORD_SOUND_CELL, "Spatial Audio");

        softAssert.assertAll();
        return this;
    }

    private void softAssertCellValue(By locator, String expected) {
        WebElement cell = driver.findElement(locator);
        softAssert.assertEquals(cell.getAttribute("value"), expected,
                String.format("value of the cell '%s' should be equal to '%s', but it's '%s' instead.",
                        cell.getAttribute("name"),
                        expected,
                        cell.getAttribute("value"))
        );
    }

    public CameraPage toggleMacroControl(SwitchState state) {
        toggleCellSwitch(state, MACRO_CONTROL_CELL);

        return this;
    }

    public CameraPage toggleLensCorrection(SwitchState state) {
        toggleCellSwitch(state, LENS_CORRECTION_CELL);
        return this;
    }

    public CameraPage toggleGrid(SwitchState state) {
        toggleCellSwitch(state, GRID_CELL);
        return this;
    }

    public CameraPage toggleLevel(SwitchState state) {
        toggleCellSwitch(state, LEVEL_CELL);
        return this;
    }

    public CameraPage toggleMirrorFrontCam(SwitchState state) {
        toggleCellSwitch(state, MIRROR_FRONT_CAM_CELL);
        return this;
    }

    public CameraPage toggleViewOutsideFrame(SwitchState state) {
        toggleCellSwitch(state, VIEW_OUTSIDE_FRAME_CELL);
        return this;
    }

    private void toggleCellSwitch(SwitchState state, By locator) {
        WebElement cell = driver.findElement(locator);
        // to scroll down to the cell without changing the switch state
        cell.findElement(AppiumBy.iOSClassChain("XCUIElementTypeStaticText")).click();
        String cellValue = cell.getAttribute("value");
        System.out.printf("cell value: %s%n", cellValue);

        SwitchState currentSwitchState = SwitchState.fromString(cellValue);

        if (!currentSwitchState.equals(state)) {
            cell.click();
        }

        Assert.assertEquals(cell.getAttribute("value"), state.getStringValue());

    }
}