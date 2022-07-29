package com.shop.entity;

import javax.persistence.*;

public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderDetailId;
    private Long productId;
    private Integer qty;
    private Double price;
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    public OrderDetail() {
    }

    public OrderDetail(Long orderDetailId, Long productId, Integer qty, Double price, Long orderId) {
        this.orderDetailId = orderDetailId;
        this.productId = productId;
        this.qty = qty;
        this.price = price;
        this.orderId = orderId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
