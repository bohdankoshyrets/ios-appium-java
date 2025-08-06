package com.bohdankoshyrets.iosappiumtests;

import com.bohdankoshyrets.iosappiumtests.pages.AboutPage;
import com.bohdankoshyrets.iosappiumtests.pages.SettingsGeneralPage;
import com.bohdankoshyrets.iosappiumtests.pages.SettingsPage;
import com.bohdankoshyrets.iosappiumtests.pages.settings.CameraPage;
import com.bohdankoshyrets.iosappiumtests.pages.enums.SwitchState;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.URL;

public class HomeScreenTest {
    private IOSDriver driver;
    private SettingsPage settings;
    private SettingsGeneralPage settingsGeneral;
    private AboutPage aboutPage;
    private CameraPage camera;

    @BeforeSuite
    public void setUpSuite() {
        System.out.println("SETUP SUITE");
    }

    @BeforeMethod
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "iOS");
        caps.setCapability("appium:platformVersion", "18.6");
        caps.setCapability("appium:deviceName", "iPhone 16");
        caps.setCapability("appium:automationName", "XCUITest");
        caps.setCapability("appium:bundleId", "com.apple.Preferences");

        driver = new IOSDriver(
                new URL("http://localhost:4723"), caps
        );

        settings = new SettingsPage(driver);
        settingsGeneral = new SettingsGeneralPage(driver);
        aboutPage = new AboutPage(driver);
        camera = new CameraPage(driver);
    }

    @Test
    public void openGeneralSettings() {

        settings.assertPageIsShown();
        settings.openGeneral();

        settingsGeneral.openAbout();
        aboutPage.assertVersionExist();
    }

    @Test
    public void checkDeviceName() {
        settings.assertPageIsShown();
        settings.openGeneral();

        settingsGeneral.openAbout();
        aboutPage.assertValueExistInNameCell();
    }

    @Test
    public void searchForFitness() {
        settings.assertPageIsShown()
                .searchFor("Fitness")
                .waitForResults()
                .selectResult("Fitness");
    }

    @Test
    public void cameraShowPalFormats() {
        settings.assertPageIsShown()
                .openCamera();

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

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}