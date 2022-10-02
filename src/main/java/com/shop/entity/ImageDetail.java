package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ImageDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imdeId;
    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Products prodImde;

    public ImageDetail() {
    }

    public ImageDetail(Long imdeId, String imageName, Products prodImde) {
        this.imdeId = imdeId;
        this.imageName = imageName;
        this.prodImde = prodImde;
    }

    public Long getImdeId() {
        return imdeId;
    }

    public void setImdeId(Long imdeId) {
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
