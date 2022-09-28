package com.shop.entity;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderDetailId;
    private Integer qty;
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Products productsOrderDetail;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Order ordersDetail;

    public OrderDetail() {
    }

    public OrderDetail(Long orderDetailId, Integer qty, Double price, Products productsOrderDetail,
                       Order ordersDetail) {
        this.orderDetailId = orderDetailId;
        this.qty = qty;
        this.price = price;
        this.ordersDetail = ordersDetail;
        this.productsOrderDetail = productsOrderDetail;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
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

    public Products getProductsOrderDetail() {
        return productsOrderDetail;
    }

    public void setProductsOrderDetail(Products productsOrderDetail) {
        this.productsOrderDetail = productsOrderDetail;
    }

    public Order getOrdersDetail() {
        return ordersDetail;
    }

    public void setOrdersDetail(Order ordersDetail) {
        this.ordersDetail = ordersDetail;
    }
}
