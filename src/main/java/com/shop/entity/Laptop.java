package com.shop.entity;

import javax.persistence.*;
import java.util.Date;

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
    private String gateway;
    private String design;
    private String special;
    private String sizeAndWeight;
    private Date yearOfManufacture;
    @Column(columnDefinition = "varchar(8000)")
    private String notes;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Products productLaptop;

    public Laptop() {
    }

    public Laptop(Long laptopId, String monitor, String cpu, String ram, String disk, String gpu,
                  String systemOperator, String gateway, String design, String special,
                  String sizeAndWeight, Date yearOfManufacture, String notes, Products productLaptop) {
        this.laptopId = laptopId;
        this.monitor = monitor;
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
        this.gpu = gpu;
        this.systemOperator = systemOperator;
        this.gateway = gateway;
        this.design = design;
        this.special = special;
        this.sizeAndWeight = sizeAndWeight;
        this.yearOfManufacture = yearOfManufacture;
        this.notes = notes;
        this.productLaptop = productLaptop;
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

    public Date getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Date yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public Products getProductLaptop() {
        return productLaptop;
    }

    public void setProductLaptop(Products productLaptop) {
        this.productLaptop = productLaptop;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
