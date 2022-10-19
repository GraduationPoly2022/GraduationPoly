package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long catId;
    private String name;


    @OneToMany(mappedBy = "catProd")
    @JsonIgnore
    private Set<Products> catProd;

    public Category() {
    }

    public Category(Long catId, String name) {
        this.catId = catId;
        this.name = name;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Products> getCatProd() {
        return catProd;
    }

    public void setCatProd(Set<Products> catProd) {
        this.catProd = catProd;
    }
}
