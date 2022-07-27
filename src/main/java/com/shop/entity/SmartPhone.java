package com.shop.entity;

import javax.persistence.*;

@Entity
public class SmartPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Products smartPhone_product;

    public SmartPhone() {
    }

    public SmartPhone(Long smartPhoneId, String monitor, String systemOperator, String beforeCamera, String afterCamera, String cpu, String ram, String disk, String batteryAndCharging, String video, String gpu, String connector, String utils, Products smartPhone_product) {
        this.smartPhoneId = smartPhoneId;
        this.monitor = monitor;
        this.systemOperator = systemOperator;
        this.beforeCamera = beforeCamera;
        this.afterCamera = afterCamera;
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
        this.batteryAndCharging = batteryAndCharging;
        this.video = video;
        this.gpu = gpu;
        this.connector = connector;
        this.utils = utils;
        this.smartPhone_product = smartPhone_product;
    }

    public Long getSmartPhoneId() {
        return smartPhoneId;
    }

    public void setSmartPhoneId(Long smartPhoneId) {
        this.smartPhoneId = smartPhoneId;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getSystemOperator() {
        return systemOperator;
    }

    public void setSystemOperator(String systemOperator) {
        this.systemOperator = systemOperator;
    }

    public String getBeforeCamera() {
        return beforeCamera;
    }

    public void setBeforeCamera(String beforeCamera) {
        this.beforeCamera = beforeCamera;
    }

    public String getAfterCamera() {
        return afterCamera;
    }

    public void setAfterCamera(String afterCamera) {
        this.afterCamera = afterCamera;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getBatteryAndCharging() {
        return batteryAndCharging;
    }

    public void setBatteryAndCharging(String batteryAndCharging) {
        this.batteryAndCharging = batteryAndCharging;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getUtils() {
        return utils;
    }

    public void setUtils(String utils) {
        this.utils = utils;
    }

    public Products getSmartPhone_product() {
        return smartPhone_product;
    }

    public void setSmartPhone_product(Products products) {
        this.smartPhone_product = products;
    }
}
