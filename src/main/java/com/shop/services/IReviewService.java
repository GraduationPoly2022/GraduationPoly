package com.shop.services;

import com.shop.dto.ReviewDto;
import com.shop.entity.Reviews;

import java.util.List;

public interface IReviewService {
    Boolean isExistByOrderIdAndUserId(Long odId, String email);

    Reviews createReview(Reviews reviews);

    Reviews findByUserAndProd(Long userId, Long prodId);

    List<ReviewDto> findAllReviews();

    Integer HandleRating(Long prodId);

    List<ReviewDto> findByUser(Long userId);

    List<ReviewDto> findByProduct(Long prodId);

    Integer countReviewsByProduct(Long prodId);
}
