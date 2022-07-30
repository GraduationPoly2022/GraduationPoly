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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private User users_shipper;

    @OneToMany(mappedBy = "shipper_orders", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Order> orders;

    @OneToOne(mappedBy = "shipper_return", cascade = CascadeType.ALL)
    @JsonIgnore
    private Return returns;

    public Shipper() {
    }

    public Shipper(Long shipperId, Double total, User users_shipper, Set<Order> orders) {
        this.shipperId = shipperId;
        this.total = total;
        this.users_shipper = users_shipper;
        this.orders = orders;
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

    public User getUsers_shipper() {
        return users_shipper;
    }

    public void setUsers_shipper(User users_shipper) {
        this.users_shipper = users_shipper;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
