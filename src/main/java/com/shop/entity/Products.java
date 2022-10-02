package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prodId;
    private String prodName;
    private String imageUrlMain;
    private Boolean available;
    private Integer warranty;

    @OneToMany(mappedBy = "prodImde")
    @JsonIgnore
    private Set<imageDetail> imageDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category catProd;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductionCompany prodPco;

    @OneToOne(mappedBy = "spProd")
    private SmartPhone smartPhone;

    @OneToOne(mappedBy = "accessoryProduct")
    private Accessory accessoryProd;

    @OneToOne(mappedBy = "laptopProd")
    private Laptop laptop;

    @OneToMany(mappedBy = "prodOdde")
    @JsonIgnore
    private Set<OrderDetail> OrderDetails;
    @OneToMany(mappedBy = "prodComment")
    @JsonIgnore
    private Set<Comment> commentProd;

    @OneToMany(mappedBy = "prodReview")
    @JsonIgnore
    private Set<Reviews> reviewsSet = new LinkedHashSet<>();

    public Products() {
    }

    public Products(Long prodId, String prodName, String imageUrlMain,
                    Boolean available, Integer warranty,
                    Category catProd, ProductionCompany prodPco, SmartPhone smartPhone, Accessory accessoryProd, Laptop laptop) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.imageUrlMain = imageUrlMain;
        this.available = available;
        this.warranty = warranty;
        this.catProd = catProd;
        this.prodPco = prodPco;
        this.smartPhone = smartPhone;
        this.accessoryProd = accessoryProd;
        this.laptop = laptop;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getImageUrlMain() {
        return imageUrlMain;
    }

    public void setImageUrlMain(String imageUrlMain) {
        this.imageUrlMain = imageUrlMain;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<imageDetail> getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(Set<imageDetail> imageDetails) {
        this.imageDetails = imageDetails;
    }

    public Category getCatProd() {
        return catProd;
    }

    public void setCatProd(Category catProd) {
        this.catProd = catProd;
    }

    public Set<OrderDetail> getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        OrderDetails = orderDetails;
    }

    public Set<Comment> getCommentProd() {
        return commentProd;
    }

    public void setCommentProd(Set<Comment> commentProd) {
        this.commentProd = commentProd;
    }

    public ProductionCompany getProductProducer() {
        return prodPco;
    }

    public void setProductProducer(ProductionCompany productProductionCompany) {
        this.prodPco = productProductionCompany;
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

    public SmartPhone getSmartPhone() {
        return smartPhone;
    }

    public void setSmartPhone(SmartPhone smartPhone) {
        this.smartPhone = smartPhone;
    }

    public Accessory getAccessoryProd() {
        return accessoryProd;
    }

    public void setAccessoryProd(Accessory accessoryProd) {
        this.accessoryProd = accessoryProd;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
}
