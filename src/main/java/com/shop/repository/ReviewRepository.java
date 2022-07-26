package com.shop.repository;

import com.shop.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Long> {

    Optional<Reviews> findByUserReview_userIdAndProdReview_prodId(Long userId, Long prodId);

    List<Reviews> findByUserReview_userId(Long userId);

    List<Reviews> findByProdReview_prodId(Long prodId);

    Optional<Integer> countByProdReview_prodId(Long prodId);

    Boolean existsByProdReview_orderDetails_odde_odIdAndUserReview_email(Long prodReview_orderDetails_odde_odId, @NotBlank String userReview_email);
}
