package com.bohdankoshyrets.iosappiumtests.tests;

import com.bohdankoshyrets.iosappiumtests.base.BaseTest;
import com.bohdankoshyrets.iosappiumtests.pages.AboutPage;
import com.bohdankoshyrets.iosappiumtests.pages.SettingsGeneralPage;
import com.bohdankoshyrets.iosappiumtests.pages.SettingsPage;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SettingsMenuItem;
import com.bohdankoshyrets.iosappiumtests.pages.settings.CameraPage;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SwitchState;
import com.bohdankoshyrets.iosappiumtests.pages.settings.PrivacyPage;
import org.testng.annotations.*;

public class HomeScreenTest extends BaseTest {
    private SettingsPage settings;
    private SettingsGeneralPage settingsGeneral;
    private AboutPage aboutPage;
    private CameraPage camera;
    private PrivacyPage privacy;

    @BeforeSuite
    public void setUpSuite() {
        System.out.println("SETUP SUITE");
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("HOME SCREEN SETUP TEST");
        settings = new SettingsPage(driver);
        settingsGeneral = new SettingsGeneralPage(driver);
        aboutPage = new AboutPage(driver);
        camera = new CameraPage(driver);
        privacy = new PrivacyPage(driver);
    }

    @Test
    public void searchForFitness() {
        settings.assertPageIsVisible()
                .searchFor("Fitness")
                .waitForResults()
                .selectResult("Fitness");
    }

    @Test
    public void cameraShowPalFormats() {
        settings.assertPageIsVisible()
                .open(SettingsMenuItem.CAMERA_CELL);

        camera.assertPageIsShown()
                .assertDefaultValues()
                .toggleMacroControl(SwitchState.ON)
                .toggleMacroControl(SwitchState.OFF)
                .toggleMacroControl(SwitchState.ON);

        camera.toggleLensCorrection(SwitchState.ON)
                .toggleLensCorrection(SwitchState.OFF)
                .toggleLensCorrection(SwitchState.OFF)
                .toggleLensCorrection(SwitchState.ON);

        camera.toggleGrid(SwitchState.ON)
                .toggleGrid(SwitchState.OFF);

        camera.toggleLevel(SwitchState.ON)
                .toggleLevel(SwitchState.OFF)
                .toggleLevel(SwitchState.ON)
                .toggleLevel(SwitchState.OFF);

        camera.toggleMirrorFrontCam(SwitchState.ON)
                .toggleMirrorFrontCam(SwitchState.OFF)
                .toggleMirrorFrontCam(SwitchState.ON)
                .toggleMirrorFrontCam(SwitchState.OFF);

        camera.toggleViewOutsideFrame(SwitchState.ON)
                .toggleViewOutsideFrame(SwitchState.OFF)
                .toggleViewOutsideFrame(SwitchState.ON)
                .toggleViewOutsideFrame(SwitchState.OFF);
    }

    @Test
    public void shouldDisplayGeneralDescription() {
        settings.assertPageIsVisible();
        settings.open(SettingsMenuItem.GENERAL_CELL);

        settingsGeneral.assertPageIsVisible();
        settingsGeneral.assertGeneralDescriptionIsVisible();
    }

    @Test
    public void shouldOpenPrivacyPage() {
        settings.assertPageIsVisible();
        settings.open(SettingsMenuItem.PRIVACY_CELL);

        privacy.assertPageIsVisible();
        privacy.openUserTracking();
    }
}