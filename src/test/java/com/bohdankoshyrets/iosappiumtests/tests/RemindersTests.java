package com.bohdankoshyrets.iosappiumtests.tests;

import com.bohdankoshyrets.iosappiumtests.base.BaseTest;
import org.testng.annotations.Test;

public class RemindersTests extends BaseTest {
    @Test
    public void openReminders() {
        reminders.launchApp();
        reminders.assertPageIsShown();
    }
}