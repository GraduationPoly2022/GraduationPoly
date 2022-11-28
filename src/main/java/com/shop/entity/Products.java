package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prodId;
    private String prodName;
    @Column(columnDefinition = "varchar(1000)")
    private String imageUrlMain;
    private Boolean available;
    private String warranty;
    private Double priceProd;
    @Column(columnDefinition = "varchar(8000)")
    private String notes;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @OneToMany(mappedBy = "prodImde")
    @JsonIgnore
    private Set<ImageDetail> imageDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category catProd;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductionCompany prodPco;

    @OneToMany(mappedBy = "favProd", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Favorites> prodFav;

    @OneToOne(mappedBy = "spProd")
    private SmartPhone smartPhone;

    @OneToOne(mappedBy = "accessoryProduct")
    private Accessory accessoryProd;

    @OneToOne(mappedBy = "laptopProd")
    private Laptop laptop;

    @OneToMany(mappedBy = "prodOdde")
    @JsonIgnore
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "prodComment")
    @JsonIgnore
    private Set<Comment> commentProd;

    @OneToMany(mappedBy = "prodReview", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Reviews> prodReviewsSet = new LinkedHashSet<>();

    public Products() {
    }

    public Products(Long prodId, String prodName, String imageUrlMain,
                    Boolean available, String warranty,
                    Double priceProd, Date dateAdded, String notes, Category catProd, ProductionCompany prodPco,
                    SmartPhone smartPhone, Accessory accessoryProd, Laptop laptop) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.imageUrlMain = imageUrlMain;
        this.available = available;
        this.warranty = warranty;
        this.priceProd = priceProd;
        this.dateAdded = dateAdded;
        this.notes = notes;
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

    public Set<ImageDetail> getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(Set<ImageDetail> ImageDetails) {
        this.imageDetails = ImageDetails;
    }

    public Category getCatProd() {
        return catProd;
    }

    public void setCatProd(Category catProd) {
        this.catProd = catProd;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Set<Comment> getCommentProd() {
        return commentProd;
    }

    public void setCommentProd(Set<Comment> commentProd) {
        this.commentProd = commentProd;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public Set<Reviews> getProdReviewsSet() {
        return prodReviewsSet;
    }

    public void setProdReviewsSet(Set<Reviews> reviewsSet) {
        this.prodReviewsSet = reviewsSet;
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

    public ProductionCompany getProdPco() {
        return prodPco;
    }

    public void setProdPco(ProductionCompany prodPco) {
        this.prodPco = prodPco;
    }

    public Double getPriceProd() {
        return priceProd;
    }

    public void setPriceProd(Double priceProd) {
        this.priceProd = priceProd;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Favorites> getProdFav() {
        return prodFav;
    }

    public void setProdFav(Set<Favorites> prodFav) {
        this.prodFav = prodFav;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
