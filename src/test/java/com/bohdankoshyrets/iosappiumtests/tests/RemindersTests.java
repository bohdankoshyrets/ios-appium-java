package com.bohdankoshyrets.iosappiumtests.tests;

import com.bohdankoshyrets.iosappiumtests.base.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.UUID;

public class RemindersTests extends BaseTest {
    @BeforeMethod
    public void beforeMethodRemindersTests() {
        reminders.launchApp();
        reminders.dismissTutorialIfPresent();
        reminders.dismissICloudSyncIfPresent();
        reminders.assertPageIsShown();
    }

    @AfterMethod
    public void afterMethodRemindersTests() {
        reminders.terminateApp();
    }

    @Test
    public void openRemindersList() {
        reminders.openDefaultList();
        remindersList.assertPageIsShown();
    }

    @Test
    public void createReminderWithTitleOnly() {
        String reminder = UUID.randomUUID().toString().substring(0, 8);

        reminders.openDefaultList();
        remindersList.assertPageIsShown();
        remindersList.assertReminderIsNotShown(reminder);
        remindersList.tapAddNewReminder();
        remindersList.enterReminderTitle(reminder);
        remindersList.tapDone();
        remindersList.assertReminderIsShown(reminder);
        remindersList.completeReminder(reminder);
    }
}