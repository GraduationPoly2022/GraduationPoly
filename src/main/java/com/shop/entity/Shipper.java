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
    private User user_shippers;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Order shipper_orders;

    @OneToMany(mappedBy = "shippers_return", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Return> returns;


    public Shipper() {
    }

    public Shipper(Long shipperId, Double total, String notes, User user_shippers, Order shipper_orders, Set<Return> returns) {
        this.shipperId = shipperId;
        this.total = total;
        this.notes = notes;
        this.user_shippers = user_shippers;
        this.shipper_orders = shipper_orders;
        this.returns = returns;
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

    public Order getShipper_orders() {
        return shipper_orders;
    }

    public void setShipper_orders(Order shipper_orders) {
        this.shipper_orders = shipper_orders;
    }

    public Set<Return> getReturns() {
        return returns;
    }

    public void setReturns(Set<Return> returns) {
        this.returns = returns;
    }

    public User getUser_shippers() {
        return user_shippers;
    }

    public void setUser_shippers(User user_shippers) {
        this.user_shippers = user_shippers;
    }
}
