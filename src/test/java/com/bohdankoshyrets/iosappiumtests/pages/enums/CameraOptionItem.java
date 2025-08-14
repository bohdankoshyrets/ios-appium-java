 package com.bohdankoshyrets.iosappiumtests.pages.enums;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public enum CameraOptionItem {
//    NAVBAR_TITLE_LOCATOR =      AppiumBy.iOSClassChain(String.format("**/XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[`label == '%s'`]", SCREEN_TITLE));
    PHOTOGRAPHIC_STYLES_CELL("Photographic Styles"),
    RECORD_VIDEO_CELL("Record Video"),
    RECORD_SLO_MO_CELL("Record Slo-mo"),
    RECORD_SOUND_CELL("Record Sound"),
    GRID_CELL("Grid"),
    LEVEL_CELL("Level"),
    MIRROR_FRONT_CAM_CELL("Mirror Front Camera"),
    VIEW_OUTSIDE_FRAME_CELL("View Outside the Frame"),
    LENS_CORRECTION_CELL("Lens Correction"),
    MACRO_CONTROL_CELL("Macro Control");

    private final String accessibilityId;

    CameraOptionItem(String accessibilityId) {
        this.accessibilityId = accessibilityId;
    }

    public By getBy() {
        return AppiumBy.iOSNsPredicateString(String.format("type == 'XCUIElementTypeCell' AND name == '%s'", this.accessibilityId));
    }
}