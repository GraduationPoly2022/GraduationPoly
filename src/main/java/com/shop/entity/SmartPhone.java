package com.shop.entity;

import javax.persistence.*;

@Entity
@Table("smartPhone")
public class SmartPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
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
    private Products products;

    public SmartPhone() {
    }

    public SmartPhone(Long productId, String monitor, String systemOperator, String beforeCamera, String afterCamera, String cpu, String ram, String disk, String batteryAndCharging, String video, String gpu, String connector, String utils, Products products) {
        this.productId = productId;
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
        this.products = products;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
