package com.bohdankoshyrets.iosappiumtests.pages.settings;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class Privacy {
    @FindBy(id = "Location Services")
    private WebElement locationServicesCell;
}