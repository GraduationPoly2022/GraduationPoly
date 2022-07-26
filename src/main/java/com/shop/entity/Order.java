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
    private Long odId;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    @Temporal(TemporalType.DATE)
    private Date recipientDate;
    private String receiver;
    private String phoneReceiver;
    @Column(columnDefinition = "varchar(5000)")
    private String notes;
    private String addressReceiver;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentEnum paymentReceived;

    @Enumerated(EnumType.STRING)
    private DeviceEnum deviceUse;

    @ManyToOne(fetch = FetchType.EAGER)
    private User usersOd;

    @OneToMany(mappedBy = "odde", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "orderShipper")
    @JsonIgnore
    private Set<Shipper> shippers;

    @OneToOne(mappedBy = "orderReturn")
    @JsonIgnore
    private Return returns;

    public Order() {
    }

    public Order(Long odId, Date orderDate, Date deliveryDate, Date recipientDate, String receiver,
                 String phoneReceiver, String notes, String addressReceiver, OrderStatus status,
                 Double amount, PaymentEnum paymentReceived, DeviceEnum deviceUse,
                 User usersOd, Return returns) {
        this.odId = odId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.recipientDate = recipientDate;
        this.receiver = receiver;
        this.phoneReceiver = phoneReceiver;
        this.notes = notes;
        this.addressReceiver = addressReceiver;
        this.status = status;
        this.amount = amount;
        this.paymentReceived = paymentReceived;
        this.deviceUse = deviceUse;
        this.usersOd = usersOd;
        this.returns = returns;
    }

    public Long getOdId() {
        return odId;
    }

    public void setOdId(Long odId) {
        this.odId = odId;
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

    public String getPhoneReceiver() {
        return phoneReceiver;
    }

    public void setPhoneReceiver(String phoneReceiver) {
        this.phoneReceiver = phoneReceiver;
    }

    public String getAddressReceiver() {
        return addressReceiver;
    }

    public void setAddressReceiver(String addressReceiver) {
        this.addressReceiver = addressReceiver;
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

    public User getUsersOd() {
        return usersOd;
    }

    public void setUsersOd(User usersOd) {
        this.usersOd = usersOd;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
