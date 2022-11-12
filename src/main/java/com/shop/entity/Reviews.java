package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reviews {
    @EmbeddedId
    private ProductUser reviewId = new ProductUser();
    @Column(columnDefinition = "varchar(500)")
    private String content;
    private Integer rating;
    @Temporal(TemporalType.DATE)
    private Date dateReview = new Date();
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User userReview;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("prodId")
    @JsonIgnore
    @JoinColumn(name = "prod_id")
    private Products prodReview;

    private Boolean hidden;

    public Reviews() {
    }

    public Reviews(String content, Integer rating, Date dateReview, User userReview, Products prodReview, Boolean hidden) {
        this.reviewId = new ProductUser(prodReview.getProdId(), userReview.getUserId());
        this.content = content;
        this.rating = rating;
        this.dateReview = dateReview;
        this.userReview = userReview;
        this.prodReview = prodReview;
        this.hidden = hidden;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    public User getUserReview() {
        return userReview;
    }

    public void setUserReview(User userReview) {
        this.userReview = userReview;
    }

    public Products getProdReview() {
        return prodReview;
    }

    public void setProdReview(Products prodReview) {
        this.prodReview = prodReview;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public ProductUser getReviewId() {
        return reviewId;
    }

    public void setReviewId(ProductUser reviewId) {
        this.reviewId = reviewId;
    }
}
