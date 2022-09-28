package com.shop.entity;

import javax.persistence.*;

@Entity
public class imageDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String imageDetailId;
    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Products productImages;

    public imageDetail() {
    }

    public imageDetail(String imageDetailId, String imageName, Products productImages) {
        this.imageDetailId = imageDetailId;
        this.imageName = imageName;
        this.productImages = productImages;
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

    public Products getProductImages() {
        return productImages;
    }

    public void setProductImages(Products productImages) {
        this.productImages = productImages;
    }
}
