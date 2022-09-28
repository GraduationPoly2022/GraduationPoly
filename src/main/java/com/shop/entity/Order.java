package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.enumEntity.DeviceEnum;
import com.shop.enumEntity.OrderStatus;
import com.shop.enumEntity.PaymentEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
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
    private String receive;
    private String phoneReceive;
    private String addressReceive;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentEnum paymentReceived;

    @Enumerated(EnumType.STRING)
    private DeviceEnum deviceUse;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User usersOrders;

    @OneToMany(mappedBy = "ordersDetail", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "orderShipper", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Shipper> shippers;

    @OneToOne(mappedBy = "orderReturn", cascade = CascadeType.ALL)
    @JsonIgnore
    private Return returns;

    public Order() {
    }


    public Order(Long orderId, Date orderDate, Date deliveryDate, Date recipientDate, String receive,
                 String phoneReceive, String addressReceive, OrderStatus status,
                 Double amount, PaymentEnum paymentReceived, DeviceEnum deviceUse,
                 User usersOrders, Return returns) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.recipientDate = recipientDate;
        this.receive = receive;
        this.phoneReceive = phoneReceive;
        this.addressReceive = addressReceive;
        this.status = status;
        this.amount = amount;
        this.paymentReceived = paymentReceived;
        this.deviceUse = deviceUse;
        this.usersOrders = usersOrders;
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

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getPhoneReceive() {
        return phoneReceive;
    }

    public void setPhoneReceive(String phoneReceive) {
        this.phoneReceive = phoneReceive;
    }

    public String getAddressReceive() {
        return addressReceive;
    }

    public void setAddressReceive(String addressReceive) {
        this.addressReceive = addressReceive;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
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

    public Return getReturns() {
        return returns;
    }

    public void setReturns(Return returns) {
        this.returns = returns;
    }

    public User getUsersOrders() {
        return usersOrders;
    }

    public void setUsersOrders(User usersOrders) {
        this.usersOrders = usersOrders;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Set<Shipper> getShippers() {
        return shippers;
    }

    public void setShippers(Set<Shipper> shippers) {
        this.shippers = shippers;
    }
}
