package com.shop.entity;

import javax.persistence.*;

@Entity
public class imageDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String imageDetailId;
    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Products product_images;

    public imageDetail() {
    }

    public imageDetail(String imageDetailId, String imageName, Products product_images) {
        this.imageDetailId = imageDetailId;
        this.imageName = imageName;
        this.product_images = product_images;
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

    public Products getProduct_images() {
        return product_images;
    }

    public void setProduct_images(Products product_images) {
        this.product_images = product_images;
    }
}
