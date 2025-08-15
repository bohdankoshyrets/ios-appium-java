package com.bohdankoshyrets.iosappiumtests.pages.enums;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

@SuppressWarnings("unused")
public enum SettingsMenuItem {
    GENERAL_CELL("com.apple.settings.general"),
    ACCESSIBILITY_CELL("com.apple.settings.accessibility"),
    ACTION_BUTTON_CELL("com.apple.settings.actionButton"),
    SIRI_CELL("com.apple.settings.siri"),
    CAMERA_CELL("com.apple.settings.camera"),
    HOME_SCREEN_CELL("com.apple.settings.homeScreen"),
    SEARCH_CELL("com.apple.settings.search"),
    STANDBY_CELL("com.apple.settings.standBy"),
    APPS_CELL("com.apple.settings.apps"),

    PRIVACY_CELL("com.apple.settings.privacyAndSecurity");

    private final String accessibilityId;

    SettingsMenuItem(String accessibilityId) {
        this.accessibilityId = accessibilityId;
    }

    public By getLocator() {
        return AppiumBy.accessibilityId(accessibilityId);
    }
}