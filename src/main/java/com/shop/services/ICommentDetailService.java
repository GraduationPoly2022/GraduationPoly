package com.shop.services;

import com.shop.dto.CommentDetailDto;
import com.shop.entity.CommentDetail;

import java.util.List;

public interface ICommentDetailService {


    CommentDetail addCommentDetail(CommentDetail commentDetail);


    List<CommentDetailDto> findCommentDtById(Long commentId);
}
