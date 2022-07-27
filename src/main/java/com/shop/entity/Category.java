package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "categories_product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Products> categories_product;

    public Category() {
    }

    public Category(Long categoryId, String categoryName, Set<Products> categories_product) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categories_product = categories_product;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Products> getCategories_product() {
        return categories_product;
    }

    public void setCategories_product(Set<Products> categories_product) {
        this.categories_product = categories_product;
    }
}
