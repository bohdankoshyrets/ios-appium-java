package com.bohdankoshyrets.iosappiumtests.pages;

import com.bohdankoshyrets.iosappiumtests.base.BasePage;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SwipeDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.*;

public class KeyboardsListPage extends BasePage {
    private static final By EDIT_NAV_BUTTON_BY = AppiumBy.iOSClassChain("**/XCUIElementTypeNavigationBar/XCUIElementTypeButton[`name == 'Edit'`]");
    private static final By DONE_BUTTON_BY = AppiumBy.iOSClassChain("**/XCUIElementTypeNavigationBar/XCUIElementTypeButton[`name == 'Done'`]");
    private static final By ADD_NEW_KEYBOARD_BY = AppiumBy.accessibilityId("AddNewKeyboard");
    private static final By DELETE_KEYBOARD_BY = AppiumBy.accessibilityId("Delete");
    private static final By MONOLINGUAL_CELL_BY = AppiumBy.iOSClassChain("**/XCUIElementTypeCell[`name == 'Monolingual'`]");
    private static final By KEYBOARD_CELL_LIST_BY = AppiumBy.iOSClassChain("**/XCUIElementTypeTable/XCUIElementTypeCell");
    private List<WebElement> getKeyboardCellElements(Locale locale) {
        return driver.findElements(
                AppiumBy.iOSClassChain("**/XCUIElementTypeCell[`name BEGINSWITH '" + locale + "'`]")
        );
    }

    public KeyboardsListPage(IOSDriver driver) {
        super(driver);
    }

    public void tapEditButton() {
        WebElement editButton = wait.until(
                ExpectedConditions.elementToBeClickable(EDIT_NAV_BUTTON_BY)
        );
        editButton.click();
    }

    public void tapDoneButton() {
        WebElement doneButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("Done"))
        );
        doneButton.click();
    }

    public void dragKeyboardToTop(Locale keyboardName) {

        WebElement firstCellDragHandle = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell[1]/XCUIElementTypeButton[`name BEGINSWITH 'Reorder'`]"));
        WebElement keyboardDragHandle = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell[`name BEGINSWITH '" + keyboardName.toString() + "'`]/XCUIElementTypeButton[`name BEGINSWITH 'Reorder'`]"));

        Point destinationPoint = new Point(
                firstCellDragHandle.getLocation().getX() + firstCellDragHandle.getSize().width / 2,
                firstCellDragHandle.getLocation().getY() + firstCellDragHandle.getSize().height / 2
        );

        Point keyboardPoint = new Point(
                keyboardDragHandle.getLocation().getX() + keyboardDragHandle.getSize().width / 2,
                keyboardDragHandle.getLocation().getY() + keyboardDragHandle.getSize().height / 2
        );

        dragFrom(keyboardPoint, destinationPoint);
    }

    public Boolean isKeyboardPresent(Locale keyboardName) {
        List<WebElement> keyboardCells = getKeyboardCellElements(keyboardName);
        return !keyboardCells.isEmpty();
    }

    public void assertKeyboardIsPresent(Locale keyboardName) {
        Assert.assertTrue(isKeyboardPresent(keyboardName), "Expected keyboard to be present, but it was not");
    }

    public void assertKeyboardIsNotPresent(Locale keyboardName) {
        Assert.assertFalse(isKeyboardPresent(keyboardName), "Expected keyboard to not be present, but it was");
    }

    public void assertKeyboardIsAtTop(Locale keyboardName) {
        List<WebElement> cellList = driver.findElements(KEYBOARD_CELL_LIST_BY);
        Assert.assertFalse(cellList.isEmpty(), "Expected at least one cell, but none were found");
        Assert.assertTrue(cellList.get(0).getAttribute("name").contains(keyboardName.toString()), "Expected keyboard to be at top, but it was not");
    }

    public void addNewKeyboard(Locale locale) {
        WebElement addKeyboardButton = driver.findElement(ADD_NEW_KEYBOARD_BY);
        addKeyboardButton.click();

        List<WebElement> keyboardCells = getKeyboardCellElements(locale);
        if (keyboardCells.isEmpty()) {
            Assert.fail("Keyboard not found");
        }
        keyboardCells.get(0).click();

        WebElement monolingualKeyboard = driver.findElement(
                MONOLINGUAL_CELL_BY
        );

        // in case the language has multiple layouts, select the first one
        if (monolingualKeyboard.isDisplayed()) {
            monolingualKeyboard.click();

            List<WebElement> layoutList = getKeyboardCellElements(locale);
            layoutList.get(0).click();
            driver.findElement(DONE_BUTTON_BY).click();
        }
    }

    public void removeKeyboard(Locale locale, Boolean assertKeyboardExists) {
        List<WebElement> keyboardCells = getKeyboardCellElements(locale);
        if (keyboardCells.isEmpty()) {
            if (assertKeyboardExists) {
                Assert.fail("Keyboard not found");
            }
            return;
        }
        swipe(keyboardCells.get(0), SwipeDirection.LEFT);
        WebElement deleteButton = driver.findElement(DELETE_KEYBOARD_BY);
        deleteButton.click();
    }

    public void removeExtraKeyboards() {
        List<WebElement> keyboardCells = driver.findElements(AppiumBy.iOSNsPredicateString("name != 'AddNewKeyboard' AND type == 'XCUIElementTypeCell'"));
        Assert.assertFalse(keyboardCells.isEmpty(), "There should be at least one keyboards present");
        if (keyboardCells.size() == 1) {
            return;
        }
        for (int i = 0; i < keyboardCells.size() - 1; i++) {
            WebElement cell = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell[`name != 'AddNewKeyboard'`][" + (keyboardCells.size() - i) + "]"));
            swipe(cell, SwipeDirection.LEFT);
            WebElement deleteButton = driver.findElement(DELETE_KEYBOARD_BY);
            deleteButton.click();
            wait.until(ExpectedConditions.invisibilityOf(deleteButton));
        }
    }

    public void assertEditButtonIsDisabled() {
        WebElement editButton = driver.findElement(EDIT_NAV_BUTTON_BY);
        Assert.assertTrue(editButton.isDisplayed(), "Edit button should be displayed");
        Assert.assertFalse(editButton.isEnabled(), "Edit button should be disabled");
    }
}