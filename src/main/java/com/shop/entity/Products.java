package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private String imageMain;
    private Boolean available;
    private Integer warranty;

    @OneToMany(mappedBy = "productImages")
    @JsonIgnore
    private Set<ImageDetail> ImageDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category categoriesProduct;


    @OneToMany(mappedBy = "smartPhoneProduct")
    @JsonIgnore
    private Set<SmartPhone> smartPhone;

    @OneToMany(mappedBy = "productAccessories")
    @JsonIgnore
    private Set<Accessory> accessories;

    @OneToMany(mappedBy = "productLaptop")
    @JsonIgnore
    private Set<Laptop> laptops;

    @OneToMany(mappedBy = "productsOrderDetail")
    @JsonIgnore
    private Set<OrderDetail> OrderDetails;
    @OneToMany(mappedBy = "productComment")
    @JsonIgnore
    private Set<Comment> commentProduct;

    @OneToMany(mappedBy = "productReview")
    @JsonIgnore
    private Set<Reviews> reviewsSet = new LinkedHashSet<>();

    public Products() {
    }

    public Products(Long productId, String productName, String imageMain,
                    Boolean available, Integer warranty,
                    Category categoriesProduct) {
        this.productId = productId;
        this.productName = productName;
        this.imageMain = imageMain;
        this.available = available;
        this.warranty = warranty;
        this.categoriesProduct = categoriesProduct;

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


    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<ImageDetail> getImageDetails() {
        return ImageDetails;
    }

    public void setImageDetails(Set<ImageDetail> ImageDetails) {
        this.ImageDetails = ImageDetails;
    }

    public Category getCategoriesProduct() {
        return categoriesProduct;
    }

    public void setCategoriesProduct(Category categoriesProduct) {
        this.categoriesProduct = categoriesProduct;
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

    public Set<Comment> getCommentProduct() {
        return commentProduct;
    }

    public void setCommentProduct(Set<Comment> commentProduct) {
        this.commentProduct = commentProduct;
    }


    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }

    public Set<Reviews> getProductReviewsSet() {
        return reviewsSet;
    }

    public void setProductReviewsSet(Set<Reviews> reviewsSet) {
        this.reviewsSet = reviewsSet;
    }
}
