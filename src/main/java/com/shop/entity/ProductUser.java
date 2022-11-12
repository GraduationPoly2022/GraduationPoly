package com.shop.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductUser implements Serializable {
    @Column(name = "prod_id")
    private Long prodId;
    @Column(name = "user_id")
    private Long userId;

    public ProductUser() {
    }

    public ProductUser(Long prodId, Long userId) {
        this.prodId = prodId;
        this.userId = userId;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
