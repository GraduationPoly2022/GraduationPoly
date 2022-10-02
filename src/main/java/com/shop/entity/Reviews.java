package com.shop.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;
    @Column(columnDefinition = "varchar(500)")
    private String content;
    private Integer rating;
    @Temporal(TemporalType.DATE)
    private Date dateReview;
    @ManyToOne(fetch = FetchType.EAGER)
    private User userReview;
    @ManyToOne(fetch = FetchType.EAGER)
    private Products prodReview;

    public Reviews() {
    }

    public Reviews(Long reviewId, String content, Integer rating, Date dateReview,
                   User userReview, Products prodReview) {
        this.reviewId = reviewId;
        this.content = content;
        this.rating = rating;
        this.dateReview = dateReview;
        this.userReview = userReview;
        this.prodReview = prodReview;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
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
}
