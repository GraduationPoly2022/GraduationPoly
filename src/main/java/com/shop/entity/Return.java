package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Returns")
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long returnId;
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    private String reason;


    @Column(columnDefinition = "varchar(5000)")


    private String notes;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Order order_return;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Order shipper_return;

    public Return() {
    }

    public Return(Long returnId, Date returnDate, String reason, String notes, Order order_return, Order shipper_return) {
        this.returnId = returnId;
        this.returnDate = returnDate;
        this.reason = reason;
        this.notes = notes;
        this.order_return = order_return;
        this.shipper_return = shipper_return;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Order getOrder_return() {
        return order_return;
    }

    public void setOrder_return(Order order_return) {
        this.order_return = order_return;
    }

    public Order getShipper_return() {
        return shipper_return;
    }

    public void setShipper_return(Order shipper_return) {
        this.shipper_return = shipper_return;
    }
}
