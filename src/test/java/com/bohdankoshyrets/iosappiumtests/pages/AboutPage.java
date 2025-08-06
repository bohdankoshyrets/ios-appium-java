package com.bohdankoshyrets.iosappiumtests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AboutPage {
    private final IOSDriver driver;

    public AboutPage(IOSDriver driver) {
        this.driver = driver;
    }

    private WebElement versionCell() {
        return driver.findElement(AppiumBy.iOSNsPredicateString("name == 'iOS Version' AND type == 'XCUIElementTypeCell'"));
    }

    private WebElement nameCell() {
        return driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell[`name == 'Name'`]"));
    }

    public void assertVersionExist() {
        String versionTitle = versionCell().getText();
        Assert.assertNotNull(versionTitle);
    }

    public void assertValueExistInNameCell() {
        String value = nameCell().getAttribute("value");
        System.out.println("value: " + value);

        Assert.assertNotNull(value);
    }
}