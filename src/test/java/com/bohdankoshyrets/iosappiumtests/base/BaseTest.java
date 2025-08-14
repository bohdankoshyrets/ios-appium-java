package com.bohdankoshyrets.iosappiumtests.base;

import com.bohdankoshyrets.iosappiumtests.config.AppiumCapabilities;
import com.bohdankoshyrets.iosappiumtests.pages.KeyboardPage;
import com.bohdankoshyrets.iosappiumtests.pages.KeyboardsListPage;
import com.bohdankoshyrets.iosappiumtests.pages.SettingsGeneralPage;
import com.bohdankoshyrets.iosappiumtests.pages.SettingsPage;
import com.bohdankoshyrets.iosappiumtests.pages.settings.CameraPage;
import com.bohdankoshyrets.iosappiumtests.pages.settings.PrivacyPage;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.URL;

public class BaseTest {
    protected IOSDriver driver;
    protected KeyboardPage keyboard;
    protected KeyboardsListPage keyboardList;
    protected SettingsPage settings;
    protected SettingsGeneralPage settingsGeneral;
    protected CameraPage camera;
    protected PrivacyPage privacy;


    @BeforeMethod
    public void setUp() throws Exception {
        System.out.println("BASE TEST SETUP TESTS");
        DesiredCapabilities caps = AppiumCapabilities.getCapabilities();

        driver = new IOSDriver(
                new URL("http://localhost:4723"), caps
        );

        keyboard = new KeyboardPage(driver);
        keyboardList = new KeyboardsListPage(driver);
        settings = new SettingsPage(driver);
        settingsGeneral = new SettingsGeneralPage(driver);
        camera = new CameraPage(driver);
        privacy = new PrivacyPage(driver);


    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}