package com.shop.services;

import com.shop.entity.CommentDetail;
import com.shop.entity.LikeReply;
import com.shop.entity.User;

import java.util.Optional;

public interface ILikeReplyService {

    LikeReply saveOrUpdateReply(LikeReply likeReply);

    LikeReply findCommentUserReply(CommentDetail cmdeId, User userCmde);

    Integer countLikeRep(Long id);

    Integer countDislikeRep(Long cmdeId);

    Optional<LikeReply> deleteCommentReply(CommentDetail cmdeId, User userCmde);
}
