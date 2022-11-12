package com.shop.repository;

import com.shop.entity.Comment;
import com.shop.entity.LikeComment;
import com.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeComment, Long> {

    Optional<LikeComment> findByCmtLkAndUserLk(Comment comment, User user);

    Integer countByCommentUser_commentIdAndEmotionTrue(Long id);


    Integer countByCommentUser_commentIdAndEmotionFalse(Long commentId);

    @Transactional
    Optional<LikeComment> deleteByCmtLkAndUserLk(Comment commentId, User userId);


}
