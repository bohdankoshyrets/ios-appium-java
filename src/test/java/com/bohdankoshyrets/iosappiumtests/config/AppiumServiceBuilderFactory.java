package com.bohdankoshyrets.iosappiumtests.config;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.net.URL;

public class AppiumServiceBuilderFactory {
    AppiumServiceBuilder builder;
    AppiumDriverLocalService service;

    public void startAppiumService() {
        builder = new AppiumServiceBuilder();
        builder.usingAnyFreePort();

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public void stopAppiumService() {
        service.stop();
    }

    public URL getServiceUrl() {
        return service.getUrl();
    }
}