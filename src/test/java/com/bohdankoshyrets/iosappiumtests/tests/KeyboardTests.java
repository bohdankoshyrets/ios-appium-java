package com.bohdankoshyrets.iosappiumtests.tests;

import com.bohdankoshyrets.iosappiumtests.base.BaseTest;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SettingsMenuItem;
import org.testng.annotations.Test;

import java.util.Locale;

public class KeyboardTests extends BaseTest {
    @Test
    public void reorderKeyboard() {
        Locale chosenKeyboardTag = new Locale("de", "DE");

        settings.assertPageIsVisible();
        settings.open(SettingsMenuItem.GENERAL_CELL);

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
        settings.open(SettingsMenuItem.GENERAL_CELL);

        settingsGeneral.assertPageIsVisible();
        settingsGeneral.openKeyboard();

        keyboard.assertPageIsVisible();
        keyboard.openKeyboards();

        keyboardList.assertKeyboardIsPresent(chosenKeyboardTag);
        keyboardList.removeKeyboard(chosenKeyboardTag);
        keyboardList.assertKeyboardIsNotPresent(chosenKeyboardTag);
        keyboardList.addNewKeyboard(chosenKeyboardTag);
    }
}