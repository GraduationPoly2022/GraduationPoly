package com.shop.helper.handleCode;

public class TimeCode {
    private Long timeExpired;
    private String code;

    private String email;

    public TimeCode() {
    }

    public TimeCode(Long timeExpired, String code, String email) {
        this.timeExpired = timeExpired;
        this.code = code;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}