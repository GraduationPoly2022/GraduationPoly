package com.shop.services.Impl;

import com.shop.entity.Comment;
import com.shop.entity.LikeComment;
import com.shop.entity.User;
import com.shop.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeCommentServiceImpl implements com.shop.services.ILikeCommentService {

    @Autowired
    LikeRepository likeRepository;

    @Override
    public LikeComment findCommentUser(Comment comment, User user) {
        return this.likeRepository.findByCmtLkAndUserLk(comment, user).orElse(null);
    }

    @Override
    public LikeComment saveOrUpdate(LikeComment likeComment) {
        return this.likeRepository.save(likeComment);
    }

    @Override
    public Integer countLike(Long id) {
        return this.likeRepository.countByCommentUser_commentIdAndEmotionTrue(id);
    }

    @Override
    public Integer countDislike(Long commentId) {
        return this.likeRepository.countByCommentUser_commentIdAndEmotionFalse(commentId);
    }


    @Override
    public Optional<LikeComment> deleteComment(Comment commentId, User userId) {
        return this.likeRepository.deleteByCmtLkAndUserLk(commentId, userId);
    }

    @Override
    public LikeComment getLikeOrDislike(Long userid, Long commentId) {
        return this.likeRepository.findByUserLk_userIdAndCmtLk_commentId(userid, commentId).orElse(null);
    }
}
