package com.shop.entity;

import javax.persistence.*;

@Entity
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accessoryId;
    private String connector;
    private String length;
    private String switches;
    private double typeKeyboard;
    private double sizeKey;
    private String special;
    private double sizeKeyboard;
    private String charging;
    @Column(columnDefinition = "varchar(8000)")
    private String notes;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Products product_accessories;

    public Accessory() {
    }

    public Accessory(Long accessoryId, String connector, String length, String switches, double typeKeyboard, double sizeKey, String special, double sizeKeyboard, String charging, String notes, Products product_accessories) {
        this.accessoryId = accessoryId;
        this.connector = connector;
        this.length = length;
        this.switches = switches;
        this.typeKeyboard = typeKeyboard;
        this.sizeKey = sizeKey;
        this.special = special;
        this.sizeKeyboard = sizeKeyboard;
        this.charging = charging;
        this.notes = notes;
        this.product_accessories = product_accessories;
    }

    public Long getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Long accessoryId) {
        this.accessoryId = accessoryId;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSwitches() {
        return switches;
    }

    public void setSwitches(String switches) {
        this.switches = switches;
    }

    public double getTypeKeyboard() {
        return typeKeyboard;
    }

    public void setTypeKeyboard(double typeKeyboard) {
        this.typeKeyboard = typeKeyboard;
    }

    public double getSizeKey() {
        return sizeKey;
    }

    public void setSizeKey(double sizeKey) {
        this.sizeKey = sizeKey;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public double getSizeKeyboard() {
        return sizeKeyboard;
    }

    public void setSizeKeyboard(double sizeKeyboard) {
        this.sizeKeyboard = sizeKeyboard;
    }

    public String getCharging() {
        return charging;
    }

    public void setCharging(String charging) {
        this.charging = charging;
    }

    public Products getProduct_accessories() {
        return product_accessories;
    }

    public void setProduct_accessories(Products product_accessories) {
        this.product_accessories = product_accessories;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
