package com.shop.entity;

import javax.persistence.*;

@Entity
public class imageDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String imdeId;
    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Products prodImde;

    public imageDetail() {
    }

    public imageDetail(String imdeId, String imageName, Products prodImde) {
        this.imdeId = imdeId;
        this.imageName = imageName;
        this.prodImde = prodImde;
    }

    public String getImdeId() {
        return imdeId;
    }

    public void setImdeId(String imdeId) {
        this.imdeId = imdeId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Products getProdImde() {
        return prodImde;
    }

    public void setProdImde(Products prodImde) {
        this.prodImde = prodImde;
    }
}
