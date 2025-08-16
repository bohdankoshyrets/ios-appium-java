package com.bohdankoshyrets.iosappiumtests.tests;

import com.bohdankoshyrets.iosappiumtests.base.BaseTest;
import org.testng.annotations.*;

import java.util.Locale;

import static com.bohdankoshyrets.iosappiumtests.pages.enums.SettingsMenuItem.*;


public class KeyboardTests extends BaseTest {

    @BeforeMethod
    public void beforeMethodKeyboardTests() {
        settings.activateApp();
    }

    @AfterMethod
    public void afterMethodKeyboardTests() {
        settings.terminateApp();
    }

    @Test
    public void reorderKeyboard() {
        Locale chosenKeyboardTag = new Locale("de", "DE");

        settings.assertPageIsVisible();
        settings.open(GENERAL_CELL);

        settingsGeneral.assertPageIsVisible();
        settingsGeneral.openKeyboard();

        keyboard.assertPageIsVisible();
        keyboard.openKeyboards();

        keyboardList.tapEditButton();
        keyboardList.dragKeyboardToTop(chosenKeyboardTag);
        keyboardList.tapDoneButton();
        keyboardList.assertKeyboardIsAtTop(chosenKeyboardTag);
    }

    @Test
    public void addKeyboard() {
        Locale chosenKeyboardTag = new Locale("de", "DE");

        settings.assertPageIsVisible();
        settings.open(GENERAL_CELL);

        settingsGeneral.assertPageIsVisible();
        settingsGeneral.openKeyboard();

        keyboard.assertPageIsVisible();
        keyboard.openKeyboards();

        keyboardList.assertKeyboardIsPresent(chosenKeyboardTag);
        keyboardList.removeKeyboard(chosenKeyboardTag);
        keyboardList.assertKeyboardIsNotPresent(chosenKeyboardTag);
        keyboardList.addNewKeyboard(chosenKeyboardTag);
    }

    @Test
    public void assertCannotRemoveOnlyKeyboard() {
        settings.assertPageIsVisible();
        settings.open(GENERAL_CELL);

        settingsGeneral.assertPageIsVisible();
        settingsGeneral.openKeyboard();

        keyboard.assertPageIsVisible();
        keyboard.openKeyboards();

        keyboardList.removeExtraKeyboards();
        keyboardList.assertEditButtonIsDisabled();
    }

    @Test
    public void openReminders() {
        settings.launchReminders();
    }
}