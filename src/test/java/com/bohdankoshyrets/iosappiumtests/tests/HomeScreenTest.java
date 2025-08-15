package com.bohdankoshyrets.iosappiumtests.tests;

import com.bohdankoshyrets.iosappiumtests.base.BaseTest;
import com.bohdankoshyrets.iosappiumtests.pages.*;
import com.bohdankoshyrets.iosappiumtests.pages.settings.userTracking.UserTrackingPage;
import org.testng.annotations.*;

import static com.bohdankoshyrets.iosappiumtests.pages.enums.SettingsMenuItem.*;


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
    public void assertSettingsOpens() {
        settings.assertPageIsVisible();
    }

    @Test(dependsOnMethods = "assertSettingsOpens")
    public void shouldDisplayGeneralDescription() {
        settings.assertPageIsVisible();
        settings.open(GENERAL_CELL);

        settingsGeneral.assertPageIsVisible();
        settingsGeneral.assertGeneralDescriptionIsVisible();
    }

    @Test(
            enabled = false,
            dependsOnMethods = "assertSettingsOpens",
            description = "[Disabled due to a bug in Simulator iOS 18.6] Asserts that searching within app works"
    )
    public void searchForFitness() {
        String searchQuery = "Fitness";

        settings.assertPageIsVisible();
        settings.searchFor(searchQuery);
        settings.waitForResults();
        settings.selectResult(searchQuery);
    }

    @Test
    public void shouldOpenPrivacyPage() {
        settings.assertPageIsVisible();
        settings.open(PRIVACY_CELL);

        privacy.assertPageIsVisible();
        privacy.openUserTracking();

        userTracking.assertPageIsVisible();
    }
}