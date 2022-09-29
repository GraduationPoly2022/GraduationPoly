package com.shop.services;

import com.shop.entity.CommentDetail;

import java.util.List;

public interface ICommentDetailService {
    CommentDetail addCommentDetail(CommentDetail commentDetail);

    List<CommentDetail> findAllCommentDetail();
}
