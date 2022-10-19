package com.shop.services.Impl;

import com.shop.entity.CommentDetail;
import com.shop.repository.CommentDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDetailServiceImpl implements com.shop.services.ICommentDetailService {
    @Autowired
    private CommentDetailRepository commentDetailRepository;

    @Override
    public CommentDetail addCommentDetail(CommentDetail commentDetail) {
        return this.commentDetailRepository.save(commentDetail);
    }

    @Override
    public List<CommentDetail> findCommentDtById(Long CommentId) {
        return this.commentDetailRepository.findByCmde_commentId(CommentId);
    }
}
