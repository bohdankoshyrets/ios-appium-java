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

    @Test(description = "[Fix or refactor]")
    public void shouldMoveChosenKeyboardToTop() {
        Locale chosenKeyboardTag = new Locale("de", "DE");

        settings.assertPageIsVisible();
        settings.open(GENERAL_CELL);

        settingsGeneral.assertPageIsVisible();
        settingsGeneral.openKeyboard();

        keyboard.assertPageIsVisible();
        keyboard.openKeyboards();

        if (!keyboardList.isKeyboardPresent(chosenKeyboardTag)) {
            keyboardList.addNewKeyboard(chosenKeyboardTag);
        }
        keyboardList.assertKeyboardIsPresent(chosenKeyboardTag);
        keyboardList.tapEditButton();
        keyboardList.dragKeyboardToTop(chosenKeyboardTag);
        keyboardList.tapDoneButton();
        keyboardList.assertKeyboardIsAtTop(chosenKeyboardTag);
    }

    @Test
    public void shouldAddChosenKeyboard() {
        Locale chosenKeyboardTag = new Locale("fr", "FR");

        ensureKeyboardIsRemoved(chosenKeyboardTag);
        keyboardList.addNewKeyboard(chosenKeyboardTag);
        keyboardList.assertKeyboardIsPresent(chosenKeyboardTag);
    }

    private void ensureKeyboardIsRemoved(Locale chosenKeyboardTag) {
        settings.assertPageIsVisible();
        settings.open(GENERAL_CELL);

        settingsGeneral.assertPageIsVisible();
        settingsGeneral.openKeyboard();

        keyboard.assertPageIsVisible();
        keyboard.openKeyboards();

        keyboardList.removeKeyboard(chosenKeyboardTag, false);
        keyboardList.assertKeyboardIsNotPresent(chosenKeyboardTag);
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
}