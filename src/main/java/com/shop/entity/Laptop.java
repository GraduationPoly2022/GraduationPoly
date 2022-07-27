package com.shop.entity;

import javax.persistence.*;

@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long laptopId;
    private String monitor;
    private String cpu;
    private String ram;
    private String disk;
    private String gpu;
    private String systemOperator;
    private String Gateway;
    private String design;
    private String special;
    private String sizeAndWeight;
    private Integer yearOfManufacture;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Products product_laptop;

    public Laptop() {
    }

    public Laptop(Long laptopId, String monitor, String cpu, String ram, String disk, String gpu, String systemOperator, String gateway, String design, String special,
                  String sizeAndWeight, Integer yearOfManufacture, Products product_laptop) {
        this.laptopId = laptopId;
        this.monitor = monitor;
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
        this.gpu = gpu;
        this.systemOperator = systemOperator;
        Gateway = gateway;
        this.design = design;
        this.special = special;
        this.sizeAndWeight = sizeAndWeight;
        this.yearOfManufacture = yearOfManufacture;
        this.product_laptop = product_laptop;
    }

    public Long getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(Long laptopId) {
        this.laptopId = laptopId;
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

    public String getSystemOperator() {
        return systemOperator;
    }

    public void setSystemOperator(String systemOperator) {
        this.systemOperator = systemOperator;
    }

    public String getGateway() {
        return Gateway;
    }

    public void setGateway(String gateway) {
        Gateway = gateway;
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

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public Products getProduct_laptop() {
        return product_laptop;
    }

    public void setProduct_laptop(Products product_laptop) {
        this.product_laptop = product_laptop;
    }
}
