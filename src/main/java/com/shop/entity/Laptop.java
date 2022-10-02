package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Laptop {

    @Id
    @Column(name = "laptop_id")
    @JsonIgnore
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
    private Date yom; //year Of Manufacture
    @Column(columnDefinition = "varchar(8000)")
    private String notes;

    public Laptop() {
    }

    public Laptop(String monitor, String cpu, String ram, String disk, String gpu,
                  String sysop, String gateway, String design, String special,
                  String sizeAndWeight, Date yom, String notes) {
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
        this.yom = yom;
        this.notes = notes;
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

    public Date getYom() {
        return yom;
    }

    public void setYom(Date yom) {
        this.yom = yom;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
}
