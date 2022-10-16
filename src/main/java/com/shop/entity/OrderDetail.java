package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long oddeId;
    private Integer qty;
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Products prodOdde;
    @ManyToOne(fetch = FetchType.LAZY)
    private Order odde;

    public OrderDetail() {
    }

    public OrderDetail(Long oddeId, Integer qty, Double price, Products prodOdde,
                       Order odde) {
        this.oddeId = oddeId;
        this.qty = qty;
        this.price = price;
        this.odde = odde;
        this.prodOdde = prodOdde;
    }

    public Long getOddeId() {
        return oddeId;
    }

    public void setOddeId(Long oddeId) {
        this.oddeId = oddeId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Products getProdOdde() {
        return prodOdde;
    }

    public void setProdOdde(Products prodOdde) {
        this.prodOdde = prodOdde;
    }

    public Order getOdde() {
        return odde;
    }

    public void setOdde(Order odde) {
        this.odde = odde;
    }
}
