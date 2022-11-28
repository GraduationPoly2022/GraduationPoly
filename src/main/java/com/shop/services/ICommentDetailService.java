package com.shop.services;

import com.shop.dto.CommentDetailDto;
import com.shop.entity.CommentDetail;

import java.util.List;

public interface ICommentDetailService {


    CommentDetail addCommentDetail(CommentDetail commentDetail);


    //convert count like,dislike reply Admin
    List<CommentDetailDto> findCommentDtById(Long commentId);

    List<CommentDetailDto> findCommentDtById(Long commentId, Long userId);


}
