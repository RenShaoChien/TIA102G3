package com.tia102g3.coachmember.model;

public enum CoachStatus {
    ACTIVE("已審核"),     // 0
    INACTIVE("未審核"), // 1
    SUSPENDED("暫停"); // 2

    private final String displayValue;

    CoachStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

