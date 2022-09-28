package com.shop.entity;

import com.shop.enumEntity.Reason;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "returns")
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long returnId;
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @Enumerated(EnumType.STRING)
    private Reason reason;
    @Column(columnDefinition = "varchar(5000)")
    private String notes;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Order orderReturn;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Shipper shippersReturn;

    public Return() {
    }

    public Return(Long returnId, Date returnDate, Reason reason, String notes,
                  Order orderReturn, Shipper shippersReturn) {
        this.returnId = returnId;
        this.returnDate = returnDate;
        this.reason = reason;
        this.notes = notes;
        this.orderReturn = orderReturn;
        this.shippersReturn = shippersReturn;
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

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Order getOrderReturn() {
        return orderReturn;
    }

    public void setOrderReturn(Order orderReturn) {
        this.orderReturn = orderReturn;
    }

    public Shipper getShippersReturn() {
        return shippersReturn;
    }

    public void setShippersReturn(Shipper shippersReturn) {
        this.shippersReturn = shippersReturn;
    }
}
