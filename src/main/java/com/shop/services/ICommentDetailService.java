package com.shop.services;

import com.shop.entity.CommentDetail;

import java.util.List;

public interface ICommentDetailService {


    public CommentDetail addCommentDetail(CommentDetail commentDetail);


    public List<CommentDetail> findCommentDtById(Long CommentId);
}
