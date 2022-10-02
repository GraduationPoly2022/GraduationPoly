package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Accessory {
    @Id
    @Column(name = "accessory_id")
    @JsonIgnore
    private Long accessoryId;

    @OneToOne
    @JoinColumn(name = "accessory_id")
    @JsonIgnore
    @MapsId
    private Products accessoryProduct;

    private String connector;
    private String length;
    private String switches;
    private Double typeKeyboard;
    private Double sizeKey;
    private String special;
    private Double sizeKeyboard;
    private String charging;
    @Column(columnDefinition = "varchar(8000)")
    private String notes;

    public Accessory() {
    }

    public Accessory(String connector, String length, String switches,
                     Double typeKeyboard, Double sizeKey, String special,
                     Double sizeKeyboard, String charging, String notes) {
        this.connector = connector;
        this.length = length;
        this.switches = switches;
        this.typeKeyboard = typeKeyboard;
        this.sizeKey = sizeKey;
        this.special = special;
        this.sizeKeyboard = sizeKeyboard;
        this.charging = charging;
        this.notes = notes;
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

    public Double getTypeKeyboard() {
        return typeKeyboard;
    }

    public void setTypeKeyboard(Double typeKeyboard) {
        this.typeKeyboard = typeKeyboard;
    }

    public Double getSizeKey() {
        return sizeKey;
    }

    public void setSizeKey(Double sizeKey) {
        this.sizeKey = sizeKey;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public Double getSizeKeyboard() {
        return sizeKeyboard;
    }

    public void setSizeKeyboard(Double sizeKeyboard) {
        this.sizeKeyboard = sizeKeyboard;
    }

    public String getCharging() {
        return charging;
    }

    public void setCharging(String charging) {
        this.charging = charging;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Products getAccessoryProduct() {
        return accessoryProduct;
    }

    public void setAccessoryProduct(Products accessoryProduct) {
        this.accessoryProduct = accessoryProduct;
    }
}
