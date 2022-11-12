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

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private IReviewService iReviewService;

    @PostMapping("/")
    //Add a review to the review table
    public ResponseEntity<ResponseMessage> createReview(@RequestBody ReviewDto reviewDto) {
        ResponseEntity<ResponseMessage> message = null;
        try {
            if (reviewDto.getProdReview().getProdId() != null) {
                Reviews reviews = new Reviews();
                BeanUtils.copyProperties(reviewDto, reviews, "reviewId");
                Reviews reviewSave = this.iReviewService.createReview(reviews);
                if (reviewSave != null) {
                    message = ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(StatusMessage.OK, "Created review is successfully!", reviewSave));
                }
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "Failed" + e.getMessage(), null));
        }
        return message;
    }

    @PatchMapping("/update_hidden")
    //Update the hidden column of the review table
    public ResponseEntity<ResponseMessage> HandlerHidden(@RequestParam("userId") Long userId, @RequestParam("prodId") Long prodId) {
        ResponseEntity<ResponseMessage> message = null;
        try {
            Reviews reviewFind = this.iReviewService.
                    findByUserAndProd(userId, prodId);
            if (reviewFind != null) {
                reviewFind.setHidden(!reviewFind.getHidden());
                Reviews reviewSave = this.iReviewService.createReview(reviewFind);
                if (reviewSave != null) {
                    message = ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(StatusMessage.OK, "Updated review is successfully!", reviewSave));
                }
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "Failed" + e.getMessage(), null));
        }
        return message;
    }

    @GetMapping("/")
    //Find all reviews
    public ResponseEntity<ResponseMessage> findAll() {
        List<Reviews> reviews = this.iReviewService.findAllReviews();
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", reviews));
    }

    @GetMapping("/find-by-user/{userId}")
    //Find reviews by user id
    public ResponseEntity<ResponseMessage> findByUser(@PathVariable("userId") Long userId) {
        List<Reviews> reviews = this.iReviewService.findByUser(userId);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", reviews));
    }

    @GetMapping("/find-by-prod/{prodId}")
    //Find reviews by product id
    public ResponseEntity<ResponseMessage> findByProduct(@PathVariable("prodId") Long prodId) {
        List<Reviews> reviews = this.iReviewService.findByProduct(prodId);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", reviews));
    }

    @GetMapping("/rating/{prodId}")
    //Average rating of a product by product id
    public ResponseEntity<ResponseMessage> findAvgRating(@PathVariable("prodId") Long prodId) {
        Integer ratingFind = this.iReviewService.HandleRating(prodId);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get rating", "Rating avg: " + ratingFind));

    }

    @GetMapping("/count-reviews/{prodId}")
    //Count Reviews by Product
    public ResponseEntity<ResponseMessage> countReviews(@PathVariable("prodId") Long prodId) {
        Integer countReviews = this.iReviewService.countReviewsByProduct(prodId);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get rating", countReviews));
    }
}