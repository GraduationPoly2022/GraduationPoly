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
    private Products products_orderDetail;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Order orders_orderDetail;

    public OrderDetail() {
    }

    public OrderDetail(Long orderDetailId, Integer qty, Double price, Products products_orderDetail, Order orders_orderDetail) {
        this.orderDetailId = orderDetailId;
        this.qty = qty;
        this.price = price;
        this.orders_orderDetail = orders_orderDetail;
        this.products_orderDetail = products_orderDetail;
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

    public Products getProducts_orderDetail() {
        return products_orderDetail;
    }

    public void setProducts_orderDetail(Products products_orderDetail) {
        this.products_orderDetail = products_orderDetail;
    }

    public Order getOrders_orderDetail() {
        return orders_orderDetail;
    }

    public void setOrders_orderDetail(Order orders_orderDetail) {
        this.orders_orderDetail = orders_orderDetail;
    }
}
