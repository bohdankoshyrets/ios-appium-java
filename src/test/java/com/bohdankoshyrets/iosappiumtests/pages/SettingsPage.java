package com.bohdankoshyrets.iosappiumtests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SettingsPage {
    private final IOSDriver driver;
    private final WebDriverWait wait;

    public SettingsPage(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    private static final Logger LOG = LoggerFactory.getLogger(SettingsPage.class);
    private static final By GENERAL_BUTTON = AppiumBy.accessibilityId("General");
    private static final By CAMERA_BUTTON = AppiumBy.accessibilityId("Camera");
    private static final By NAV_BAR_TITLE = AppiumBy.iOSClassChain("**/XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[`label == 'Settings'`]");
    private static final By SEARCH_FIELD = AppiumBy.iOSClassChain("**/XCUIElementTypeSearchField[`label == 'Search'`]");
    private static final By SEARCH_COLLECTION_VIEW = AppiumBy.iOSClassChain("**/XCUIElementTypeCollectionView[`name == 'com.apple.settings.applicationSearch.collectionView'`]");
    private static final By SEARCH_NO_RESULTS_COLLECTION_VIEW = AppiumBy.iOSClassChain("**/XCUIElementTypeCollectionView[`name == 'com.apple.settings.zeroKeywordSearch.collectionView'`]");
    private static By resultCell(String query) {
        return AppiumBy.iOSClassChain("**/XCUIElementTypeCell/**/XCUIElementTypeStaticText[`name == '" + query + "'`]");
    }

    private WebElement generalCell() {
        LOG.info("generalCell");
        LOG.debug("that's a debug");
        LOG.error("wow big ERROR!!");
        return driver.findElement(GENERAL_BUTTON);
    }

    private WebElement generalNavBarTitle() {
        return driver.findElement(NAV_BAR_TITLE);
    }

    private WebElement searchField() {
        return driver.findElement(SEARCH_FIELD);
    }

//    @FindBy(id = "Search")
//    private WebElement searchField;

    public SettingsPage assertPageIsShown() {
        Assert.assertTrue(generalCell().isDisplayed(), "Settings Page is not displayed");
        Assert.assertTrue(generalNavBarTitle().isDisplayed());
//        Assert.assertTrue(searchField.isDisplayed());
        return this;
    }

    public void openGeneral() {
        WebElement generalCell =
                wait.until(
                        ExpectedConditions.elementToBeClickable(GENERAL_BUTTON)
                );
        generalCell.click();
    }

    public void openCamera() {
        WebElement cameraCell =
                wait.until(
                        ExpectedConditions.elementToBeClickable(CAMERA_BUTTON)
                );
        cameraCell.click();
    }

    public SettingsPage searchFor(String query) {
        searchField().click();
        wait.until(ExpectedConditions.elementToBeClickable(SEARCH_NO_RESULTS_COLLECTION_VIEW));
        searchField().sendKeys(query);
        return this;
    }

    public SettingsPage waitForResults() {
        wait.until(ExpectedConditions.elementToBeClickable(SEARCH_COLLECTION_VIEW));
        return this;
    }

    public SettingsPage selectResult(String query) {
        WebElement foundCell =
                wait.until(
                        ExpectedConditions.elementToBeClickable(resultCell(query))
                );
        Assert.assertTrue(foundCell.isDisplayed());
        foundCell.click();
        return this;
    }
}