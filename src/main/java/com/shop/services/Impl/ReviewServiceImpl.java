package com.shop.services.Impl;

import com.shop.entity.Reviews;
import com.shop.repository.ReviewRepository;
import com.shop.services.IReviewService;
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


    @SafeVarargs
    public final void getModelFind(List<Reviews> reviewsList, List<Reviews>... reviewFind) {
        if (reviewFind.length > 0) {
            for (List<Reviews> list : reviewFind
            ) {
                for (Reviews reviews : list) {
                    Reviews review = new Reviews();
                    review.setReviewId(reviews.getReviewId());
                    review.setUserReview(reviews.getUserReview());
                    review.setProdReview(reviews.getProdReview());
                    review.setDateReview(reviews.getDateReview());
                    review.setContent(reviews.getContent());
                    review.setRating(reviews.getRating());
                    review.setHidden(reviews.getHidden());
                    reviewsList.add(review);
                }
            }

        }
    }

    @Override
    public Reviews createReview(Reviews reviews) {
        return this.reviewRepository.save(reviews);
    }

    @Override
    public Reviews findByUserAndProd(Long userId, Long prodId) {
        return this.reviewRepository.findByUserReview_userIdAndProdReview_prodId(userId, prodId).orElse(null);
    }

    @Override
    public List<Reviews> findAllReviews() {
        return this.reviewRepository.findAll();
    }

    @Override
    public Integer HandleRating(Long prodId) {
        Double reviewFind = this.reviewRepository.findAll().stream()
                .filter(item -> Objects.equals(item.getProdReview().getProdId(), prodId))
                .collect(Collectors.averagingInt(Reviews::getRating));
        return reviewFind.intValue();
    }

    @Override
    public List<Reviews> findByUser(Long userId) {
        return this.getModelByUser(userId);
    }

    @Override
    public List<Reviews> findByProduct(Long prodId) {
        return this.getModelByProd(prodId);
    }

    @Override
    public Integer countReviewsByProduct(Long prodId) {
        return reviewRepository.countByProdReview_prodId(prodId).orElse(null);
    }

    private List<Reviews> getModelByUser(Long userId) {
        //get review by user
        List<Reviews> reviewList = new ArrayList<>();
        List<Reviews> reviewFind = this.reviewRepository.findByUserReview_userId(userId);
        getModelFind(reviewList, reviewFind);
        return reviewList;
    }

    private List<Reviews> getModelByProd(Long prodId) {
        //get review by product
        List<Reviews> reviewList = new ArrayList<>();
        List<Reviews> reviewFind = this.reviewRepository.findByProdReview_prodId(prodId);
        getModelFind(reviewList, reviewFind);
        return reviewList;
    }

}
