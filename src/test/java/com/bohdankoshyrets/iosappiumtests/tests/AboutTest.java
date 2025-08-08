package com.bohdankoshyrets.iosappiumtests.tests;

import com.bohdankoshyrets.iosappiumtests.base.BaseTest;
import com.bohdankoshyrets.iosappiumtests.pages.AboutPage;
import com.bohdankoshyrets.iosappiumtests.pages.SettingsGeneralPage;
import com.bohdankoshyrets.iosappiumtests.pages.SettingsPage;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SettingsMenuItem;
import org.testng.annotations.*;

public class AboutTest extends BaseTest {
    private SettingsPage settings;
    private SettingsGeneralPage settingsGeneral;
    private AboutPage aboutPage;

    @BeforeMethod
    public void setUp() {
        settings = new SettingsPage(driver);
        settingsGeneral = new SettingsGeneralPage(driver);
        aboutPage = new AboutPage(driver);
    }

    @Test(description = "Open General Settings and check version")
    public void openGeneralSettings() {
        settings.assertPageIsVisible();
        settings.open(SettingsMenuItem.GENERAL_CELL);

        settingsGeneral.assertPageIsVisible();
        settingsGeneral.openAbout();

        aboutPage.assertPageIsVisible();
        aboutPage.assertVersionExist();
    }

    @Test(description = "Should display correct device name and version")
    public void shouldDisplayCorrectDeviceNameAndVersion() {
        settings.assertPageIsVisible();
        settings.open(SettingsMenuItem.GENERAL_CELL);

        settingsGeneral.assertPageIsVisible();
        settingsGeneral.openAbout();

        aboutPage.assertPageIsVisible();
        aboutPage.assertDeviceNameEqualsCapabilities();
        aboutPage.assertVersionEqualsCapabilities();
    }
}