package com.shop.repository;

import com.shop.entity.CommentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDetailRepository extends JpaRepository<CommentDetail, Long> {
    List<CommentDetail> findByCmde_commentId(Long CommentId);
}
