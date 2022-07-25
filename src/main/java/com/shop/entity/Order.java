package com.shop.entity;

import java.util.Date;

public class Order {
    private Long orderId;
    private Date orderDate;
    private Date deliveryDate;
    private Date recipientDate;
    private String receiver;
    private Long phoneReciver;
    private String addressReciver;
    private String status;

    private Double amount;

    public Order() {
    }

    public Order(Long orderId, Date orderDate, Date deliveryDate, Date recipientDate, String receiver, Long phoneReciver, String addressReciver, String status, Double amount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.recipientDate = recipientDate;
        this.receiver = receiver;
        this.phoneReciver = phoneReciver;
        this.addressReciver = addressReciver;
        this.status = status;
        this.amount = amount;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Long getPhoneReciver() {
        return phoneReciver;
    }

    public void setPhoneReciver(Long phoneReciver) {
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
