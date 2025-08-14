package com.bohdankoshyrets.iosappiumtests.tests;

import com.bohdankoshyrets.iosappiumtests.base.BaseTest;
import com.bohdankoshyrets.iosappiumtests.pages.*;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SettingsMenuItem;
import com.bohdankoshyrets.iosappiumtests.pages.settings.userTracking.UserTrackingPage;
import org.testng.annotations.*;

public class HomeScreenTest extends BaseTest {
    private UserTrackingPage userTracking;

    @BeforeSuite
    public void setUpSuite() {
        System.out.println("SETUP SUITE");
        // TODO: Remove from final build
    }

    @BeforeMethod
    public void setUpHomeScreen() {
        System.out.println("HOME SCREEN SETUP TEST");
        userTracking = new UserTrackingPage(driver);
        // TODO: Remove from final build
    }

    @Test
    public void shouldDisplayGeneralDescription() {
        settings.assertPageIsVisible();
        settings.open(SettingsMenuItem.GENERAL_CELL);

        settingsGeneral.assertPageIsVisible();
        settingsGeneral.assertGeneralDescriptionIsVisible();
    }

    @Test
    public void searchForFitness() {
        settings.assertPageIsVisible();
        settings.searchFor("Fitness");
        settings.waitForResults();
        settings.selectResult("Fitness");
        // TODO: Add assert for fitness page
    }

    @Test
    public void shouldOpenPrivacyPage() {
        settings.assertPageIsVisible();
        settings.open(SettingsMenuItem.PRIVACY_CELL);

        privacy.assertPageIsVisible();
        privacy.openUserTracking();

        userTracking.assertPageIsVisible();
    }
}