package com.shop.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long RId;
    @Column(columnDefinition = "varchar(500)")
    private String content;
    private Integer rating;
    @Temporal(TemporalType.DATE)
    private Date dateReview;
    @ManyToOne(fetch = FetchType.EAGER)
    private User userReview;
    @ManyToOne(fetch = FetchType.EAGER)
    private Products productReview;

    public Reviews() {
    }

    public Reviews(Long RId, String content, Integer rating, Date dateReview,
                   User userReview, Products productReview) {
        this.RId = RId;
        this.content = content;
        this.rating = rating;
        this.dateReview = dateReview;
        this.userReview = userReview;
        this.productReview = productReview;
    }

    public Long getRId() {
        return RId;
    }

    public void setRId(Long RId) {
        this.RId = RId;
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

    public Products getProductReview() {
        return productReview;
    }

    public void setProductReview(Products productReview) {
        this.productReview = productReview;
    }
}
