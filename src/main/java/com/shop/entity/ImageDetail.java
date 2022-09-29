package com.shop.entity;

import javax.persistence.*;

@Entity
public class ImageDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageDetailId;
    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Products productImages;

    public ImageDetail() {
    }

    public ImageDetail(Long imageDetailId, String imageName, Products productImages) {
        this.imageDetailId = imageDetailId;
        this.imageName = imageName;
        this.productImages = productImages;
    }

    public Long getImageDetailId() {
        return imageDetailId;
    }

    public void setImageDetailId(Long imageDetailId) {
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
