package com.shop.services.Impl;

import com.shop.dto.CommentDetailDto;
import com.shop.entity.CommentDetail;
import com.shop.entity.LikeReply;
import com.shop.repository.CommentDetailRepository;
import com.shop.services.ILikeReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentDetailServiceImpl implements com.shop.services.ICommentDetailService {
    @Autowired
    private CommentDetailRepository commentDetailRepository;
    @Autowired
    private ILikeReplyService iLikeReplyService;

    @Override
    public CommentDetail addCommentDetail(CommentDetail commentDetail) {
        return this.commentDetailRepository.save(commentDetail);
    }

    //convert count like,dislike reply Admin
    @Override
    public List<CommentDetailDto> findCommentDtById(Long commentId) {
        List<CommentDetailDto> commentDetailDtoList = new ArrayList<>();
        List<CommentDetail> commentDetailFind = this.commentDetailRepository.findByCmde_commentId(commentId);
        if (!commentDetailFind.isEmpty()) {
            for (CommentDetail commentDetail : commentDetailFind) {
                CommentDetailDto commentDetailDto = new CommentDetailDto();
                getCommentDetail(commentDetailDto, commentDetail);
                commentDetailDtoList.add(commentDetailDto);
            }
        }
        return commentDetailDtoList;
    }

    //convert count like,dislike reply User
    @Override
    public List<CommentDetailDto> findCommentDtById(Long commentId, Long userId) {
        List<CommentDetailDto> commentDetailDtoList = new ArrayList<>();
        List<CommentDetail> commentDetailFind = this.commentDetailRepository.findByCmde_commentId(commentId);
        if (!commentDetailFind.isEmpty()) {
            for (CommentDetail commentDetail : commentDetailFind) {
                CommentDetailDto commentDetailDto = new CommentDetailDto();
                getCommentDetail(commentDetailDto, commentDetail);
                LikeReply likeOrDislikeReply = this.iLikeReplyService.getLikeOrDislike(userId,
                        commentDetail.getCmdeId());
                if (likeOrDislikeReply != null) {
                    commentDetailDto.setLikeOrDislikeReply(likeOrDislikeReply.getEmotion());
                }
                commentDetailDtoList.add(commentDetailDto);
            }
        }
        return commentDetailDtoList;
    }

    private void getCommentDetail(CommentDetailDto commentDetailDto, CommentDetail commentDetail) {
        commentDetailDto.setCmdeId(commentDetail.getCmdeId());
        commentDetailDto.setContentReply(commentDetail.getContentReply());
        commentDetailDto.setContentDateReply(commentDetail.getContentDateReply());
        commentDetailDto.setCmde(commentDetail.getCmde());
        commentDetailDto.setUserCmde(commentDetail.getUserCmde());
        commentDetailDto.setUserReply(commentDetail.getUserReply());
        Integer countLike = this.iLikeReplyService.countLikeRep(commentDetail.getCmdeId());
        if (countLike != null) {
            commentDetailDto.setLikeReply(countLike);
        }
        Integer countDisLike = this.iLikeReplyService.countDislikeRep(commentDetail.getCmdeId());
        if (countDisLike != null) {
            commentDetailDto.setDisLikeReply(countDisLike);
        }
    }
}
