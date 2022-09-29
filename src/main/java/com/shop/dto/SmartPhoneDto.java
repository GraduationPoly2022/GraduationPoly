package com.shop.dto;

import com.shop.entity.ImageDetail;
import com.shop.entity.Producer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Producer smartPhoneProducer;
    private List<ImageDetail> imageDetailList;
}

