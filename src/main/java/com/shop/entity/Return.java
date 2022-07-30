package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "returns")
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
    private Order order_return;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Shipper shippers_return;

    public Return() {
    }

    public Return(Long returnId, Date returnDate, String reason, String notes, Order order_return, Shipper shippers_return1) {
        this.returnId = returnId;
        this.returnDate = returnDate;
        this.reason = reason;
        this.notes = notes;
        this.order_return = order_return;
        this.shippers_return = shippers_return1;
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

    public Shipper getShippers_return() {
        return shippers_return;
    }

    public void setShippers_return(Shipper shippers_return) {
        this.shippers_return = shippers_return;
    }
}
