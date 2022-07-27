package com.shop.entity;

import javax.persistence.*;

@Entity
public class imageDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String imageDetailId;
    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Products products;

    public imageDetail() {
    }

    public imageDetail(String imageDetailId, String imageName, Products products) {
        this.imageDetailId = imageDetailId;
        this.imageName = imageName;
        this.products = products;
    }

    public String getImageDetailId() {
        return imageDetailId;
    }

    public void setImageDetailId(String imageDetailId) {
        this.imageDetailId = imageDetailId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
