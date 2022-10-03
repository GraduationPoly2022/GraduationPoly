package com.shop.helper.handleCode;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TimeCode {
    private Long timeExpired;
    @JsonIgnore
    private String code;

    public TimeCode() {
    }

    public TimeCode(Long timeExpired, String code) {
        this.timeExpired = timeExpired;
        this.code = code;
    }

    public Long getTimeExpired() {
        return timeExpired;
    }

    public void setTimeExpired(Long timeExpired) {
        this.timeExpired = timeExpired;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}