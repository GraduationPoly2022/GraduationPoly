package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Accessory {
    @Id
    @Column(name = "accessory_id")
    private Long accessoryId;

    @OneToOne
    @JoinColumn(name = "accessory_id")
    @JsonIgnore
    @MapsId
    private Products accessoryProduct;

    private String connector;
    private String length;
    private String switches;
    private String typeKeyboard;
    private String sizeKey;
    private String special;
    private String sizeKeyboard;
    private String charging;

    public Accessory() {
    }

    public Accessory(Long accessoryId, Products accessoryProduct, String connector, String length,
                     String switches, String typeKeyboard, String sizeKey, String special,
                     String sizeKeyboard, String charging) {
        this.accessoryId = accessoryId;
        this.accessoryProduct = accessoryProduct;
        this.connector = connector;
        this.length = length;
        this.switches = switches;
        this.typeKeyboard = typeKeyboard;
        this.sizeKey = sizeKey;
        this.special = special;
        this.sizeKeyboard = sizeKeyboard;
        this.charging = charging;
    }

    public Long getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Long accessoryId) {
        this.accessoryId = accessoryId;
    }

    public Products getAccessoryProduct() {
        return accessoryProduct;
    }

    public void setAccessoryProduct(Products accessoryProduct) {
        this.accessoryProduct = accessoryProduct;
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

    public String getTypeKeyboard() {
        return typeKeyboard;
    }

    public void setTypeKeyboard(String typeKeyboard) {
        this.typeKeyboard = typeKeyboard;
    }

    public String getSizeKey() {
        return sizeKey;
    }

    public void setSizeKey(String sizeKey) {
        this.sizeKey = sizeKey;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getSizeKeyboard() {
        return sizeKeyboard;
    }

    public void setSizeKeyboard(String sizeKeyboard) {
        this.sizeKeyboard = sizeKeyboard;
    }

    public String getCharging() {
        return charging;
    }

    public void setCharging(String charging) {
        this.charging = charging;
    }
}
