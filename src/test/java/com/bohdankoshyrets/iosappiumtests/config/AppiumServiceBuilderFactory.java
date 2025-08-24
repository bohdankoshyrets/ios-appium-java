package com.bohdankoshyrets.iosappiumtests.config;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.net.URL;

public class AppiumServiceBuilderFactory {
    private final AppiumServiceBuilder builder;
    private AppiumDriverLocalService service;

    public AppiumServiceBuilderFactory() {
        this.builder = new AppiumServiceBuilder();
        builder.usingAnyFreePort();
    }

    public synchronized void startAppiumService() {
        if (service == null || !service.isRunning()) {
            service = AppiumDriverLocalService.buildService(builder);
            service.start();
        }
    }

    public synchronized void stopAppiumService() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }

    public synchronized URL getServiceUrl() {
        if (service != null && service.isRunning()) {
            return service.getUrl();
        }
        throw new IllegalStateException("Appium server is not running");
    }
}
