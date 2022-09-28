package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shipperId;
    private Double total;

    @Column(columnDefinition = "varchar(5000)")
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User userShippers;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Order orderShipper;

    @OneToMany(mappedBy = "shippersReturn", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Return> returns;


    public Shipper() {
    }

    public Shipper(Long shipperId, Double total, String notes, User userShippers, Order orderShipper) {
        this.shipperId = shipperId;
        this.total = total;
        this.notes = notes;
        this.userShippers = userShippers;
        this.orderShipper = orderShipper;
    }

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Set<Return> getReturns() {
        return returns;
    }

    public void setReturns(Set<Return> returns) {
        this.returns = returns;
    }

    public User getUserShippers() {
        return userShippers;
    }

    public void setUserShippers(User userShippers) {
        this.userShippers = userShippers;
    }

    public Order getOrderShipper() {
        return orderShipper;
    }

    public void setOrderShipper(Order orderShipper) {
        this.orderShipper = orderShipper;
    }
}
