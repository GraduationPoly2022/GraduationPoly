package com.shop.entity;

import javax.persistence.*;

@Entity
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long producerId;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Products producer_product;

    public Producer() {
    }

    public Producer(Long producerId, String name, Products producer_product) {
        this.producerId = producerId;
        this.name = name;
        this.producer_product = producer_product;
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Products getProducer_product() {
        return producer_product;
    }

    public void setProducer_product(Products producer_product) {
        this.producer_product = producer_product;
    }
}
