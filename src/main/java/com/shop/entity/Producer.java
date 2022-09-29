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

    @OneToMany(mappedBy = "accessoriesProducer")
    @JsonIgnore
    private Set<Accessory> accessoriesProducer;

    @OneToMany(mappedBy = "smartPhoneProducer")
    @JsonIgnore
    private Set<SmartPhone> smartPhoneProducerSet;

    @OneToMany(mappedBy = "laptopProducer")
    @JsonIgnore
    private Set<Laptop> laptopProducerSet;

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

    public Set<Accessory> getAccessoriesProducer() {
        return accessoriesProducer;
    }

    public void setAccessoriesProducer(Set<Accessory> accessoriesProducer) {
        this.accessoriesProducer = accessoriesProducer;
    }

    public Set<SmartPhone> getSmartPhoneProducerSet() {
        return smartPhoneProducerSet;
    }

    public void setSmartPhoneProducerSet(Set<SmartPhone> smartPhoneProducerSet) {
        this.smartPhoneProducerSet = smartPhoneProducerSet;
    }

    public Set<Laptop> getLaptopProducerSet() {
        return laptopProducerSet;
    }

    public void setLaptopProducerSet(Set<Laptop> laptopProducerSet) {
        this.laptopProducerSet = laptopProducerSet;
    }
}
