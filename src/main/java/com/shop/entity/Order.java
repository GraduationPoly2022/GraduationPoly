package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.enumEntity.DeviceEnum;
import com.shop.enumEntity.PaymentEnum;
import com.shop.enumEntity.StatusName;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    @Temporal(TemporalType.DATE)
    private Date recipientDate;
    private String reciver;
    private String phoneReciver;
    private String addressReciver;
    @Enumerated(EnumType.STRING)
    private StatusName status;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentEnum paymentReceived;

    @Enumerated(EnumType.STRING)
    private DeviceEnum deviceUse;



    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User users_orders;

    @OneToMany(mappedBy = "orders_orderDetail", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderDetail> orderDetails;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Shipper shipper_orders;

    @OneToOne(mappedBy = "order_return", cascade = CascadeType.ALL)
    @JsonIgnore
    private Return returns;


    public Order() {
    }


    public Order(Long orderId, Date orderDate, Date deliveryDate, Date recipientDate, String reciver, String phoneReciver, String addressReciver, StatusName status, Double amount,PaymentEnum paymentReceived,DeviceEnum deviceUse,User users_orders, Set<OrderDetail> orderDetails, Shipper shipper_orders, Return returns) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.recipientDate = recipientDate;
        this.reciver = reciver;
        this.phoneReciver = phoneReciver;
        this.addressReciver = addressReciver;
        this.status = status;
        this.amount = amount;
        this.paymentReceived = paymentReceived;
        this.deviceUse = deviceUse;
        this.users_orders = users_orders;
        this.orderDetails = orderDetails;
        this.shipper_orders=shipper_orders;
        this.returns = returns;
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

    public String getPhoneReciver() {
        return phoneReciver;
    }

    public void setPhoneReciver(String phoneReciver) {
        this.phoneReciver = phoneReciver;
    }

    public String getAddressReciver() {
        return addressReciver;
    }

    public void setAddressReciver(String addressReciver) {
        this.addressReciver = addressReciver;
    }

    public StatusName getStatus() {
        return status;
    }

    public void setStatus(StatusName status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentEnum getPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(PaymentEnum paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public DeviceEnum getDeviceUse() {
        return deviceUse;
    }

    public void setDeviceUse(DeviceEnum deviceUse) {
        this.deviceUse = deviceUse;
    }

    public Shipper getShipper_orders() {
        return shipper_orders;
    }

    public void setShipper_orders(Shipper shipper_orders) {
        this.shipper_orders = shipper_orders;
    }

    public Return getReturns() {
        return returns;
    }

    public void setReturns(Return returns) {
        this.returns = returns;
    }

    public User getUsers_orders() {
        return users_orders;
    }

    public void setUsers_orders(User users_orders) {
        this.users_orders = users_orders;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }



}
