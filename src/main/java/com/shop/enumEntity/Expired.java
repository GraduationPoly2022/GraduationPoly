package com.shop.enumEntity;

public enum Expired {
    HOURS, DAYS, TIME_MOBILE;

    private final Long localTimeMill = System.currentTimeMillis();

    public Long getTime() {
        long timeMill;
        switch (this) {
            case HOURS -> {
                timeMill = localTimeMill + (1000L * 60L * 60L * 2L);
            }
            case DAYS -> {
                timeMill = localTimeMill + (1000L * 60L * 60L * 24L * 10L);
            }
            default -> {
                timeMill = localTimeMill + (1000L * 60L * 60L * 24L * 30L * 2L);
            }
        }

        return timeMill;
    }
}