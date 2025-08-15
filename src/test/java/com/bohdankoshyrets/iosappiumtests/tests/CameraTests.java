package com.bohdankoshyrets.iosappiumtests.tests;

import com.bohdankoshyrets.iosappiumtests.base.BaseTest;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SettingsMenuItem;
import org.testng.annotations.Test;

import static com.bohdankoshyrets.iosappiumtests.pages.enums.CameraOptionItem.*;
import static com.bohdankoshyrets.iosappiumtests.pages.enums.SwitchState.*;

public class CameraTests extends BaseTest {
    @Test(description = "Asserts that camera settings toggles work")
    public void assertCameraSettingsTogglesWork() {
        settings.assertPageIsVisible();
        settings.open(SettingsMenuItem.CAMERA_CELL);

        camera.assertPageIsShown();
        camera.assertDefaultValues();

        camera.toggleCameraSwitch(MACRO_CONTROL_CELL, OFF);
        camera.toggleCameraSwitch(MACRO_CONTROL_CELL, ON);

        camera.toggleCameraSwitch(LENS_CORRECTION_CELL, OFF);
        camera.toggleCameraSwitch(LENS_CORRECTION_CELL, ON);

        camera.toggleCameraSwitch(GRID_CELL, OFF);
        camera.toggleCameraSwitch(GRID_CELL, ON);

        camera.toggleCameraSwitch(LEVEL_CELL, OFF);
        camera.toggleCameraSwitch(LEVEL_CELL, ON);

        camera.toggleCameraSwitch(MIRROR_FRONT_CAM_CELL, OFF);
        camera.toggleCameraSwitch(MIRROR_FRONT_CAM_CELL, ON);

        camera.toggleCameraSwitch(VIEW_OUTSIDE_FRAME_CELL, OFF);
        camera.toggleCameraSwitch(VIEW_OUTSIDE_FRAME_CELL, ON);
    }
}