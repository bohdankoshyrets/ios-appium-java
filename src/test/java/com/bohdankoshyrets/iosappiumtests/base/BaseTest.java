package com.bohdankoshyrets.iosappiumtests.base;

import com.bohdankoshyrets.iosappiumtests.config.AppiumCapabilities;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.URL;

public class BaseTest {
    protected IOSDriver driver;

    @BeforeMethod
    public void sutUp() throws Exception {
        System.out.println("BASE TEST SETUP TESTS");
        DesiredCapabilities caps = AppiumCapabilities.getCapabilities();

        driver = new IOSDriver(
                new URL("http://localhost:4723"), caps
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}