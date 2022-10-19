package com.shop.services;

import com.shop.dto.ReviewDto;
import com.shop.entity.Reviews;

import java.util.List;

public interface IReviewService {
    Reviews createReview(Reviews reviews);

    List<ReviewDto> findAllReviews();

    Integer HandleRating(Long prodId);
}
