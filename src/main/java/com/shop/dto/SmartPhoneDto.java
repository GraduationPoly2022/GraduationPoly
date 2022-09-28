package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmartPhoneDto {
    private Long smartPhoneId;
    private String monitor;
    private String systemOperator;
    private String beforeCamera;
    private String afterCamera;
    private String cpu;
    private String ram;
    private String disk;
    private String batteryAndCharging;
    private String video;
    private String gpu;
    private String connector;
    private String utils;
    private String notes;
}
