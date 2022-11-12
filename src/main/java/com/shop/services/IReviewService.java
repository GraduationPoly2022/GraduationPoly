package com.shop.services;

import com.shop.entity.Reviews;

import java.util.List;

public interface IReviewService {
    Reviews createReview(Reviews reviews);

    Reviews findByUserAndProd(Long userId, Long prodId);

    List<Reviews> findAllReviews();

    Integer HandleRating(Long prodId);

    List<Reviews> findByUser(Long userId);

    List<Reviews> findByProduct(Long prodId);

    Integer countReviewsByProduct(Long prodId);
}
