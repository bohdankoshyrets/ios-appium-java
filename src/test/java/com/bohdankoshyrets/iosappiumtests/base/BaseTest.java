package com.bohdankoshyrets.iosappiumtests.base;

import com.bohdankoshyrets.iosappiumtests.config.AppiumCapabilities;
import com.bohdankoshyrets.iosappiumtests.config.AppiumServiceBuilderFactory;
import com.bohdankoshyrets.iosappiumtests.pages.reminders.RemindersListPage;
import com.bohdankoshyrets.iosappiumtests.pages.reminders.RemindersPage;
import com.bohdankoshyrets.iosappiumtests.pages.settings.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {
    protected IOSDriver driver;
    protected KeyboardPage keyboard;
    protected KeyboardsListPage keyboardList;
    protected SettingsPage settings;
    protected SettingsGeneralPage settingsGeneral;
    protected CameraPage camera;
    protected PrivacyPage privacy;
    protected RemindersPage reminders;
    protected RemindersListPage remindersList;

    AppiumServiceBuilderFactory factory;

    @BeforeClass
    public void setUpClass() {
        XCUITestOptions opts = AppiumCapabilities.getOptions();

        factory = new AppiumServiceBuilderFactory();
        factory.startAppiumService();

        driver = new IOSDriver(
                factory.getServiceUrl(), opts
        );

        keyboard = new KeyboardPage(driver);
        keyboardList = new KeyboardsListPage(driver);
        settings = new SettingsPage(driver);
        settingsGeneral = new SettingsGeneralPage(driver);
        camera = new CameraPage(driver);
        privacy = new PrivacyPage(driver);
        reminders = new RemindersPage(driver);
        remindersList = new RemindersListPage(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) {
        String className = result.getTestClass().getRealClass().getName();
        String methodName = result.getMethod().getMethodName();
        String fullMethodName = className + "." + methodName;
        String status = result.getStatus() == ITestResult.SUCCESS ? "passed" : "failed";
        String emojiStatus = result.getStatus() == ITestResult.SUCCESS ? "✅" : "❌";
        System.out.println(emojiStatus + fullMethodName + " " + status);
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }

        factory.stopAppiumService();
    }
}