package com.shop.services.Impl;

import com.shop.dto.ReviewDto;
import com.shop.entity.Reviews;
import com.shop.repository.ReviewRepository;
import com.shop.services.IReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements IReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Reviews createReview(Reviews reviews) {
        return this.reviewRepository.save(reviews);
    }

    @Override
    public List<ReviewDto> findAllReviews() {
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        List<Reviews> reviewFind = this.reviewRepository.findAll();
        for (Reviews reviews : reviewFind) {
            ReviewDto reviewDto = new ReviewDto();
            BeanUtils.copyProperties(reviews, reviewDto);
            reviewDto.setProdReview(reviews.getProdReview());
            reviewDto.setUserReview(reviews.getUserReview());
            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }

    @Override
    public Integer HandleRating(Long prodId) {
        Double reviewFind = this.reviewRepository.findAll().stream()
                .filter(item -> Objects.equals(item.getProdReview().getProdId(), prodId))
                .collect(Collectors.averagingInt(Reviews::getRating));
        return reviewFind.intValue();
    }

}
