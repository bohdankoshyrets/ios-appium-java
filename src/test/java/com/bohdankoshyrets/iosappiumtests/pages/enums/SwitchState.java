package com.bohdankoshyrets.iosappiumtests.pages.enums;

public enum SwitchState {
    ON("1"),
    OFF("0");

    private final String stringValue;

    SwitchState(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public static SwitchState fromString(String value) {
        if ("1".equals(value)) {
            return ON;
        } else if ("0".equals(value)) {
            return OFF;
        } else {
            throw new IllegalArgumentException("Invalid switch state value: " + value);
        }
    }
}