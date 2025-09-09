package com.bohdankoshyrets.iosappiumtests.utils;

import java.util.UUID;

public class ReminderUtils {
    public static String generateRandomTitle() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}