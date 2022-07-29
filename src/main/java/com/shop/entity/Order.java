package com.shop.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Date orderDate;
    private Date deliveryDate;
    private Date recipientDate;
    private String reciver;
    private Double phoneReciver;
    private String addressReciver;
    private String status;
    private Long userId;
    private Double Amount;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;


    public Order() {
    }

    public Order(Long orderId, Date orderDate, Date deliveryDate, Date recipientDate, String reciver, Double phoneReciver, String addressReciver, String status, Long userId, Double amount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.recipientDate = recipientDate;
        this.reciver = reciver;
        this.phoneReciver = phoneReciver;
        this.addressReciver = addressReciver;
        this.status = status;
        this.userId = userId;
        Amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getRecipientDate() {
        return recipientDate;
    }

    public void setRecipientDate(Date recipientDate) {
        this.recipientDate = recipientDate;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public Double getPhoneReciver() {
        return phoneReciver;
    }

    public void setPhoneReciver(Double phoneReciver) {
        this.phoneReciver = phoneReciver;
    }

    public String getAddressReciver() {
        return addressReciver;
    }

    public void setAddressReciver(String addressReciver) {
        this.addressReciver = addressReciver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }
}
