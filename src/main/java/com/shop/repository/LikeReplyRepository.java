package com.shop.repository;

import com.shop.entity.CommentDetail;
import com.shop.entity.LikeReply;
import com.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LikeReplyRepository extends JpaRepository<LikeReply, Long> {

    Optional<LikeReply> findByCmtRepAndUserRep(CommentDetail cmdeId, User userCmde);


    Integer countByCommentUserReply_cmdeIdAndEmotionTrue(Long id);

    Integer countByCommentUserReply_cmdeIdAndEmotionFalse(Long cmdeId);

    @Transactional
    Optional<LikeReply> deleteByCmtRepAndUserRep(CommentDetail cmdeId, User userCmde);

    Optional<LikeReply> findByUserRep_userIdAndCmtRep_cmdeId(Long userRep_userId, Long cmdeId);
}
