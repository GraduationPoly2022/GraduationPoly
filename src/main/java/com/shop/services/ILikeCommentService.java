package com.shop.services;

import com.shop.entity.Comment;
import com.shop.entity.LikeComment;
import com.shop.entity.User;

import java.util.Optional;

public interface ILikeCommentService {
    LikeComment findCommentUser(Comment comment, User user);

    LikeComment saveOrUpdate(LikeComment likeComment);

    Integer countLike(Long id);

    Integer countDislike(Long commentId);

    Optional<LikeComment> deleteComment(Comment commentId, User userId);

    LikeComment getLikeOrDislike(Long userid, Long commentId);
}
