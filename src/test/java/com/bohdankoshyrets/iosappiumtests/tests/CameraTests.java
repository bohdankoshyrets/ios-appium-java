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

        camera.toggleCellSwitch(ON, MACRO_CONTROL_CELL);
        camera.toggleCellSwitch(OFF, MACRO_CONTROL_CELL);

        camera.toggleCellSwitch(ON, LENS_CORRECTION_CELL);
        camera.toggleCellSwitch(OFF, LENS_CORRECTION_CELL);

        camera.toggleCellSwitch(ON, GRID_CELL);
        camera.toggleCellSwitch(OFF, GRID_CELL);

        camera.toggleCellSwitch(ON, LEVEL_CELL);
        camera.toggleCellSwitch(OFF, LEVEL_CELL);

        camera.toggleCellSwitch(ON, MIRROR_FRONT_CAM_CELL);
        camera.toggleCellSwitch(OFF, MIRROR_FRONT_CAM_CELL);

        camera.toggleCellSwitch(ON, VIEW_OUTSIDE_FRAME_CELL);
        camera.toggleCellSwitch(OFF, VIEW_OUTSIDE_FRAME_CELL);
    }
}