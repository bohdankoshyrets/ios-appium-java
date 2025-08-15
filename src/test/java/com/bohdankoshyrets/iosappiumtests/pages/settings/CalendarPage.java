package com.bohdankoshyrets.iosappiumtests.pages.settings;

import com.bohdankoshyrets.iosappiumtests.pages.BasePage;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SwitchState;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CalendarPage extends BasePage {
    private static final String SCREEN_TITLE = "Camera";
    private static final By NAVBAR_TITLE_LOCATOR = AppiumBy.iOSClassChain(String.format("**/XCUIElementTypeNavigationBar/[`name == '%s'`]", SCREEN_TITLE));
    private static final By WEEK_VIEW_CELL = AppiumBy.iOSNsPredicateString("name == 'Week Numbers' AND type == 'XCUIElementTypeCell'");


    public CalendarPage(IOSDriver driver) {
        super(driver);
    }

    public void assertPageIsShown() {
        wait.until(ExpectedConditions.elementToBeClickable(NAVBAR_TITLE_LOCATOR));
    }

    public void toggleWeekView(SwitchState state) {
        toggleCellSwitch(WEEK_VIEW_CELL, state);
    }
}