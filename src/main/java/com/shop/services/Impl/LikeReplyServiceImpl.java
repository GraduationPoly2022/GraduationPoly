package com.shop.services.Impl;

import com.shop.entity.CommentDetail;
import com.shop.entity.LikeReply;
import com.shop.entity.User;
import com.shop.repository.LikeReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeReplyServiceImpl implements com.shop.services.ILikeReplyService {

    @Autowired
    LikeReplyRepository likeReplyRepository;


    @Override
    public LikeReply saveOrUpdateReply(LikeReply likeReply) {
        return this.likeReplyRepository.save(likeReply);
    }

    @Override
    public LikeReply findCommentUserReply(CommentDetail cmdeId, User userCmde) {
        return this.likeReplyRepository.findByCmtRepAndUserRep(cmdeId, userCmde).orElse(null);
    }

    @Override
    public Integer countLikeRep(Long id) {
        return this.likeReplyRepository.countByCommentUserReply_cmdeIdAndEmotionTrue(id);
    }

    @Override
    public Integer countDislikeRep(Long cmdeId) {
        return this.likeReplyRepository.countByCommentUserReply_cmdeIdAndEmotionFalse(cmdeId);
    }


    @Override
    public Optional<LikeReply> deleteCommentReply(CommentDetail cmdeId, User userCmde) {
        return this.likeReplyRepository.deleteByCmtRepAndUserRep(cmdeId, userCmde);
    }
}
