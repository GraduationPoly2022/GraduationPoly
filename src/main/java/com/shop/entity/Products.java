package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private String imageMain;
    private String imageUrl;
    private boolean available;

    @OneToMany(mappedBy = "product_images", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<imageDetail> imageDetails;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Category categories_product;

    @OneToMany(mappedBy = "producer_product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Producer> producer;

    @OneToMany(mappedBy = "smartPhone_product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SmartPhone> smartPhone;

    @OneToMany(mappedBy = "product_accessories", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Accessory> accessories;

    @OneToMany(mappedBy = "product_laptop", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Laptop> laptops;

    @OneToMany(mappedBy = "products_orderDetail", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderDetail> OrderDetails;

    public Products() {
    }

    public Products(Long productId, String productName, String imageMain, String imageUrl, boolean available, Set<imageDetail> imageDetails, Category categories_product, Set<Producer> producer, Set<SmartPhone> smartPhone, Set<Accessory> accessories, Set<Laptop> laptops, Set<OrderDetail> orderDetails) {
        this.productId = productId;
        this.productName = productName;
        this.imageMain = imageMain;
        this.imageUrl = imageUrl;
        this.available = available;
        this.imageDetails = imageDetails;
        this.categories_product = categories_product;
        this.producer = producer;
        this.smartPhone = smartPhone;
        this.accessories = accessories;
        this.laptops = laptops;
        OrderDetails = orderDetails;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageMain() {
        return imageMain;
    }

    public void setImageMain(String imageMain) {
        this.imageMain = imageMain;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<imageDetail> getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(Set<imageDetail> imageDetails) {
        this.imageDetails = imageDetails;
    }

    public Category getCategories_product() {
        return categories_product;
    }

    public void setCategories_product(Category categories_product) {
        this.categories_product = categories_product;
    }

    public Set<Producer> getProducer() {
        return producer;
    }

    public void setProducer(Set<Producer> producer) {
        this.producer = producer;
    }

    public Set<SmartPhone> getSmartPhone() {
        return smartPhone;
    }

    public void setSmartPhone(Set<SmartPhone> smartPhone) {
        this.smartPhone = smartPhone;
    }

    public Set<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(Set<Accessory> accessories) {
        this.accessories = accessories;
    }

    public Set<Laptop> getLaptops() {
        return laptops;
    }

    public void setLaptops(Set<Laptop> laptops) {
        this.laptops = laptops;
    }

    public Set<OrderDetail> getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        OrderDetails = orderDetails;
    }
}
