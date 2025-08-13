package com.bohdankoshyrets.iosappiumtests.tests;

import com.bohdankoshyrets.iosappiumtests.base.BaseTest;
import com.bohdankoshyrets.iosappiumtests.pages.*;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SettingsMenuItem;
import com.bohdankoshyrets.iosappiumtests.pages.settings.CameraPage;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SwitchState;
import com.bohdankoshyrets.iosappiumtests.pages.settings.PrivacyPage;
import org.testng.annotations.*;

public class HomeScreenTest extends BaseTest {
    private CameraPage camera;
    private PrivacyPage privacy;

    @BeforeSuite
    public void setUpSuite() {
        System.out.println("SETUP SUITE");
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("HOME SCREEN SETUP TEST");
        camera = new CameraPage(driver);
        privacy = new PrivacyPage(driver);
    }

    @Test
    public void searchForFitness() {
        settings.assertPageIsVisible();
        settings.searchFor("Fitness");
        settings.waitForResults();
        settings.selectResult("Fitness");
    }

    @Test
    public void cameraShowPalFormats() {
        settings.assertPageIsVisible();
        settings.open(SettingsMenuItem.CAMERA_CELL);

        camera.assertPageIsShown();
        camera.assertDefaultValues();
        camera.toggleMacroControl(SwitchState.ON);
        camera.toggleMacroControl(SwitchState.OFF);
        camera.toggleMacroControl(SwitchState.ON);

        camera.toggleLensCorrection(SwitchState.ON);
        camera.toggleLensCorrection(SwitchState.OFF);
        camera.toggleLensCorrection(SwitchState.OFF);
        camera.toggleLensCorrection(SwitchState.ON);

        camera.toggleGrid(SwitchState.ON);
        camera.toggleGrid(SwitchState.OFF);

        camera.toggleLevel(SwitchState.ON);
        camera.toggleLevel(SwitchState.OFF);
        camera.toggleLevel(SwitchState.ON);
        camera.toggleLevel(SwitchState.OFF);

        camera.toggleMirrorFrontCam(SwitchState.ON);
        camera.toggleMirrorFrontCam(SwitchState.OFF);
        camera.toggleMirrorFrontCam(SwitchState.ON);
        camera.toggleMirrorFrontCam(SwitchState.OFF);

        camera.toggleViewOutsideFrame(SwitchState.ON);
        camera.toggleViewOutsideFrame(SwitchState.OFF);
        camera.toggleViewOutsideFrame(SwitchState.ON);
        camera.toggleViewOutsideFrame(SwitchState.OFF);
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