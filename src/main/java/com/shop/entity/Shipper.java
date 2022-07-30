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

    @OneToMany(mappedBy = "shipper_users", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<User> users;

    @OneToMany(mappedBy = "shipper_orders", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Order> orders;

    @OneToMany(mappedBy = "shipper_return", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Return> returns;

    public Shipper() {
    }

    public Shipper(Long shipperId, Double total, String notes, Set<User> users, Set<Order> orders, Set<Return> returns) {
        this.shipperId = shipperId;
        this.total = total;
        this.notes = notes;
        this.users = users;
        this.orders = orders;
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


    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
