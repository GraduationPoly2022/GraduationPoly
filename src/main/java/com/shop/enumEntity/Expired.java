package com.shop.enumEntity;

public enum Expired {
    HOURS, DAYS, TIME_MOBILE;

    private final Long localTimeMill = System.currentTimeMillis();

    public Long getTime() {
        return switch (this) {
            case HOURS -> (localTimeMill + (1000L * 60L * 60L * 2L));
            case DAYS -> (localTimeMill + (1000L * 60L * 60L * 24L * 10L));
            default -> (localTimeMill + (1000L * 60L * 60L * 24L * 30L * 2L));
        };
    }
}