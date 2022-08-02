package com.shop.enumEntity;

public enum Expired {
    HOURS(System.currentTimeMillis() + 1000L * 60L * 60L * 2L),
    DAYS(System.currentTimeMillis() + 1000L * 60L * 60L * 24L * 10L),
    TIME_MOBILE(System.currentTimeMillis() + 1000L * 60L * 60L * 24L * 30L * 2L);

    private final Long time;


    Expired(Long date) {
        this.time = date;
    }

    public Long getTime() {
        return this.time;
    }
}