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
    private Order orders_shipper;

    @OneToMany(mappedBy = "shippers_return", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Return> returns;


    public Shipper() {
    }

    public Shipper(Long shipperId, Double total, String notes, User user_shippers, Order orders_shipper) {
        this.shipperId = shipperId;
        this.total = total;
        this.notes = notes;
        this.user_shippers = user_shippers;
        this.orders_shipper = orders_shipper;
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

    public User getUser_shippers() {
        return user_shippers;
    }

    public void setUser_shippers(User user_shippers) {
        this.user_shippers = user_shippers;
    }

    public Order getOrders_shipper() {
        return orders_shipper;
    }

    public void setOrders_shipper(Order orders_shipper) {
        this.orders_shipper = orders_shipper;
    }
}
