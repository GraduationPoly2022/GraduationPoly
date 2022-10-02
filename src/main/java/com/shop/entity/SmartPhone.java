package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SmartPhone {

    @Id
    @Column(name = "sp_id")
    private Long spId;

    private String monitor;
    private String sysop;//system operator
    private String beforeCamera;
    private String afterCamera;
    private String cpu;
    private String ram;
    private String disk;
    private String batAChg;//batteryAndCharging
    private String video;
    private String gpu;
    private String connector;
    private String utils;
    @Column(columnDefinition = "varchar(8000)")
    private String notes;

    @OneToOne
    @JoinColumn(name = "sp_id")
    @JsonIgnore
    @MapsId
    private Products spProd;

    public SmartPhone() {
    }

    public SmartPhone(String monitor, String sysop, String beforeCamera,
                      String afterCamera, String cpu, String ram, String disk, String batAChg,
                      String video, String gpu, String connector, String utils,
                      String notes, Products spProd) {
        this.monitor = monitor;
        this.sysop = sysop;
        this.beforeCamera = beforeCamera;
        this.afterCamera = afterCamera;
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
        this.batAChg = batAChg;
        this.video = video;
        this.gpu = gpu;
        this.connector = connector;
        this.utils = utils;
        this.notes = notes;
        this.spProd = spProd;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getSysop() {
        return sysop;
    }

    public void setSysop(String sysop) {
        this.sysop = sysop;
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

    public String getBatAChg() {
        return batAChg;
    }

    public void setBatAChg(String batAChg) {
        this.batAChg = batAChg;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getSpId() {
        return spId;
    }

    public void setSpId(Long spId) {
        this.spId = spId;
    }

    public Products getSpProd() {
        return spProd;
    }

    public void setSpProd(Products spProd) {
        this.spProd = spProd;
    }
}
