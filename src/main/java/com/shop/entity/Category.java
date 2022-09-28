package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String name;


    @OneToMany(mappedBy = "categoriesProduct")
    @JsonIgnore
    private Set<Products> categoriesProduct;

    public Category() {

    }

    public Category(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Products> getCategoriesProduct() {
        return categoriesProduct;
    }

    public void setCategoriesProduct(Set<Products> categoriesProduct) {
        this.categoriesProduct = categoriesProduct;
    }
}
