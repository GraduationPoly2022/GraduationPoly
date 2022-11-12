package com.shop.services.Impl;

import com.shop.dto.CommentDetailDto;
import com.shop.entity.CommentDetail;
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

    //convert count like,dislike reply
    @Override
    public List<CommentDetailDto> findCommentDtById(Long commentId) {
        List<CommentDetailDto> commentDetailDtoList = new ArrayList<>();
        List<CommentDetail> commentDetailFind = this.commentDetailRepository.findByCmde_commentId(commentId);
        if (!commentDetailFind.isEmpty()) {
            for (CommentDetail commentDetail : commentDetailFind) {
                CommentDetailDto commentDetailDto = new CommentDetailDto();
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
                commentDetailDtoList.add(commentDetailDto);
            }
        }
        return commentDetailDtoList;
    }
}
