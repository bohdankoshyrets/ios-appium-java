package com.bohdankoshyrets.iosappiumtests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AboutPage {
    private final IOSDriver driver;
    private final WebDriverWait wait;
    private static final By ABOUT_CELL = AppiumBy.accessibilityId("About");
    private static final By NAV_BAR_TITLE = AppiumBy.iOSClassChain("**/XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[`label == 'About'`]");

    public AboutPage(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement versionCell() {
        return driver.findElement(AppiumBy.iOSNsPredicateString("name == 'iOS Version' AND type == 'XCUIElementTypeCell'"));
    }

    private WebElement nameCell() {
        return driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell[`name == 'Name'`]"));
    }

    private WebElement modelName() {
        return driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell[`name == 'Model Name'`]"));
    }

    public void assertPageIsVisible() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(NAV_BAR_TITLE)
        );
    }

    public void assertVersionExist() {
        String versionTitle = versionCell().getText();
        Assert.assertNotNull(versionTitle);
    }

    public void assertValueExistInNameCell() {
        String value = modelName().getAttribute("value");
        System.out.println("value: " + value);

        Assert.assertNotNull(value);
    }

    public void assertDeviceNameEqualsCapabilities() {
        String deviceName = driver.getCapabilities().getCapability("appium:deviceName").toString();
        String deviceNameCell = modelName().getAttribute("value");
        Assert.assertEquals(deviceNameCell, deviceName, "Device name does not match");
    }

    public void assertVersionEqualsCapabilities() {
        String version = driver.getCapabilities().getCapability("appium:platformVersion").toString();
        String versionCell = versionCell().getText();
        Assert.assertEquals(versionCell, version, "Version does not match");

    }
}