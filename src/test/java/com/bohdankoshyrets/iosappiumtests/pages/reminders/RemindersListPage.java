package com.bohdankoshyrets.iosappiumtests.pages.reminders;

import com.bohdankoshyrets.iosappiumtests.pages.enums.SwipeDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class RemindersListPage extends RemindersPage {
    private static final By REMINDERS_LIST = AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"RemindersList.ID.RemindersTable\"`]");
    private static final By ADD_NEW_REMINDER_BUTTON = AppiumBy.iOSNsPredicateString("name == \"New Reminder\" AND type == \"XCUIElementTypeButton\"");
    private static final By REMINDER_CELL = AppiumBy.iOSClassChain("**/XCUIElementTypeCell[`name CONTAINS 'x-apple-reminderkit'`]");
    private static final By REMINDER_CHECKBOX = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == 'circle'`]");
    private static final By REMINDER_DELETE_BUTTON = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == 'Delete'`]");
    private static final By REMINDER_DETAILS_BUTTON = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == 'Show Details'`]");
    private List<WebElement> remindersWithTitle(String title) {
        List<WebElement> remindersCells = driver.findElements(REMINDER_CELL);
        return remindersCells.stream().filter(cell -> !cell.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == '"+ title + "'`]")).isEmpty()).toList();
    }
    private WebElement findCheckboxIn(WebElement reminderCell) {
        return reminderCell.findElement(REMINDER_CHECKBOX);
    }

    public RemindersListPage(IOSDriver driver) {
        super(driver);
    }

    public void assertPageIsShown() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(REMINDERS_LIST)
        );
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void tapAddNewReminder() {
        driver.findElement(ADD_NEW_REMINDER_BUTTON).click();
    }

    public void enterReminderTitle(String reminderTitle) {
        WebElement textField = driver.findElement(AppiumBy
                .iOSNsPredicateString("name == \"Title\" AND type == \"XCUIElementTypeTextField\""));
        textField.sendKeys(reminderTitle);
    }

    public void tapDone() {
        driver.findElement(AppiumBy
                .iOSClassChain("**/XCUIElementTypeNavigationBar/XCUIElementTypeButton[`name == \"Done\"`]")).click();
    }

    public void assertReminderIsShown(String reminderTitle) {
        Assert.assertFalse(remindersWithTitle(reminderTitle).isEmpty(),
                "Reminder with title " + reminderTitle + " is not shown");
    }

    public void assertReminderIsNotShown(String reminderTitle) {
        Assert.assertTrue(remindersWithTitle(reminderTitle).isEmpty(),
                "Reminder with title " + reminderTitle + " is shown");
    }

    public void completeReminder(String reminderTitle) {
        WebElement reminderCell = remindersWithTitle(reminderTitle).get(0);
        findCheckboxIn(reminderCell).click();
        assertReminderDissapearsAfterCompletion(reminderCell);
    }

    private void assertReminderDissapearsAfterCompletion(WebElement reminderCell) {
         wait.until(ExpectedConditions.invisibilityOf(reminderCell));
    }

    public void deleteReminder(String reminderTitle) {
        WebElement reminderCell = remindersWithTitle(reminderTitle).get(0);
        deleteReminder(reminderCell);
        assertReminderDissapearsAfterCompletion(reminderCell);
    }

    private void deleteReminder(WebElement reminderCell) {
        swipe(reminderCell, SwipeDirection.LEFT);
        reminderCell.findElement(REMINDER_DELETE_BUTTON).click();
    }
}