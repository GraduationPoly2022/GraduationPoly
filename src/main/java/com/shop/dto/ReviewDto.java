package com.shop.dto;

import com.shop.entity.Products;
import com.shop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private String content;
    private Integer rating;
    private Date dateReview;
    private User userReview;
    private Products prodReview;
    private Boolean hidden;
}
