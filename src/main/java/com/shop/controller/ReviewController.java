package com.shop.controller;

import com.shop.dto.ResponseMessage;
import com.shop.dto.ReviewDto;
import com.shop.entity.Reviews;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.IReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private IReviewService iReviewService;

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createReview(@RequestBody ReviewDto reviewDto) {
        ResponseEntity<ResponseMessage> message = null;
        try {
            Reviews reviews = new Reviews();
            BeanUtils.copyProperties(reviewDto, reviews, "reviewId");
            reviews.setDateReview(new Date());
            Reviews reviewSave = this.iReviewService.createReview(reviews);
            if (reviewSave != null) {
                message = ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(StatusMessage.OK, "Created review is successfully!", reviewSave));
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "Failed" + e.getMessage(), null));
        }
        return message;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMessage> findAll() {
        ResponseEntity<ResponseMessage> message = null;
        List<ReviewDto> reviewDto = this.iReviewService.findAllReviews();
        if (!reviewDto.isEmpty()) {
            message = ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", reviewDto));
        }
        return message;
    }

    @GetMapping("/rating/{prodId}")
    public ResponseEntity<ResponseMessage> findAvgRating(@PathVariable("prodId") Long prodId) {
        ResponseEntity<ResponseMessage> message = null;
        Integer ratingFind = this.iReviewService.HandleRating(prodId);
        message = ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get rating", "Rating avg: " + ratingFind));
        return message;
    }
}