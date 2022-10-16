package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Laptop {

    @Id
    @Column(name = "laptop_id")
    private Long laptopId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "laptop_id")
    @MapsId
    private Products laptopProd;

    private String monitor;
    private String cpu;
    private String ram;
    private String disk;
    private String gpu;
    private String sysop;
    private String gateway;
    private String design;
    private String special;
    private String sizeAndWeight;
    private String batteryInfo;
    private Integer yom; //year Of Manufacture

    public Laptop() {
    }

    public Laptop(String monitor, String cpu, String ram, String disk, String gpu,
                  String sysop, String gateway, String design, String special,
                  String sizeAndWeight, String batteryInfo, Integer yom) {
        this.monitor = monitor;
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
        this.gpu = gpu;
        this.sysop = sysop;
        this.gateway = gateway;
        this.design = design;
        this.special = special;
        this.sizeAndWeight = sizeAndWeight;
        this.batteryInfo = batteryInfo;
        this.yom = yom;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
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

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getSysop() {
        return sysop;
    }

    public void setSysop(String sysop) {
        this.sysop = sysop;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getSizeAndWeight() {
        return sizeAndWeight;
    }

    public void setSizeAndWeight(String sizeAndWeight) {
        this.sizeAndWeight = sizeAndWeight;
    }

    public Integer getYom() {
        return yom;
    }

    public void setYom(Integer yom) {
        this.yom = yom;
    }

    public Products getLaptopProd() {
        return laptopProd;
    }

    public void setLaptopProd(Products laptopProd) {
        this.laptopProd = laptopProd;
    }

    public Long getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(Long laptopId) {
        this.laptopId = laptopId;
    }

    public String getBatteryInfo() {
        return batteryInfo;
    }

    public void setBatteryInfo(String batteryInfo) {
        this.batteryInfo = batteryInfo;
    }
}
