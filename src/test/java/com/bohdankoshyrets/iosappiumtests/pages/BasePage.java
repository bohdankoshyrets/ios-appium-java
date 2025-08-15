package com.bohdankoshyrets.iosappiumtests.pages;

import com.bohdankoshyrets.iosappiumtests.pages.enums.SwipeDirection;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SwitchState;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BasePage {
    protected IOSDriver driver;
    protected WebDriverWait wait;

    public BasePage(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void dragFrom(Point origin, Point destination) {
        Map<String, Object> args = new HashMap<>();
        args.put("fromX", origin.getX());
        args.put("fromY", origin.getY());
        args.put("toX", destination.getX());
        args.put("toY", destination.getY());
        args.put("duration", 1);
        driver.executeScript("mobile: dragFromToForDuration", args);
    }

    protected void swipe(WebElement element, SwipeDirection direction) {
        Map<String, Object> args = new HashMap<>();
        String elementId = ((RemoteWebElement) element).getId();
        args.put("direction", direction.name().toLowerCase());
        args.put("elementId", elementId);
        driver.executeScript("mobile: swipe", args);
    }

    protected static void sleepFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void toggleCellSwitch(By cellBy, SwitchState state) {
        WebElement cell = driver.findElement(cellBy);
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