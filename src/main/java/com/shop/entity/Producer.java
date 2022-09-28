package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long producerId;
    private String name;

    @OneToMany(mappedBy = "productProducer")
    @JsonIgnore
    private Set<Products> producer_product;

    public Producer() {
    }

    public Producer(Long producerId, String name) {
        this.producerId = producerId;
        this.name = name;
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

    public Set<Products> getProducer_product() {
        return producer_product;
    }

    public void setProducer_product(Set<Products> producer_product) {
        this.producer_product = producer_product;
    }
}
