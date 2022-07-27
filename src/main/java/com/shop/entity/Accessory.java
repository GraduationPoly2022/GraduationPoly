package com.shop.entity;

import javax.persistence.*;

@Entity
@Table("accessory")
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String connector;
    private String length;
    private String switches;
    private double typeKeyboard;
    private double sizeKey;
    private String special;
    private double sizeKeyboard;
    private String charging;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Products products;

    public Accessory() {
    }

    public Accessory(Long productId, String connector, String length, String switches, double typeKeyboard, double sizeKey, String special, double sizeKeyboard, String charging, Products products) {
        this.productId = productId;
        this.connector = connector;
        this.length = length;
        this.switches = switches;
        this.typeKeyboard = typeKeyboard;
        this.sizeKey = sizeKey;
        this.special = special;
        this.sizeKeyboard = sizeKeyboard;
        this.charging = charging;
        this.products = products;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
