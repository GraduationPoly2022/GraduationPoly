package com.shop.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ProductReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long PRId;
    @Column(columnDefinition = "varchar(500)")
    private String content;
    private Integer rating;
    @Temporal(TemporalType.DATE)
    private Date dateProductReview;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User userProductReview;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Products productReview;

    public ProductReviews() {
    }

    public ProductReviews(Long PRId, String content, Integer rating, Date dateProductReview,
                          User userProductReview, Products productReview) {
        this.PRId = PRId;
        this.content = content;
        this.rating = rating;
        this.dateProductReview = dateProductReview;
        this.userProductReview = userProductReview;
        this.productReview = productReview;
    }

    public Long getPRId() {
        return PRId;
    }

    public void setPRId(Long PRId) {
        this.PRId = PRId;
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

    public Date getDateProductReview() {
        return dateProductReview;
    }

    public void setDateProductReview(Date dateProductReview) {
        this.dateProductReview = dateProductReview;
    }

    public User getUserProductReview() {
        return userProductReview;
    }

    public void setUserProductReview(User userProductReview) {
        this.userProductReview = userProductReview;
    }

    public Products getProductReview() {
        return productReview;
    }

    public void setProductReview(Products productReview) {
        this.productReview = productReview;
    }
}
