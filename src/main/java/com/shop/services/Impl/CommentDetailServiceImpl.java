package com.shop.services.Impl;

import com.shop.entity.CommentDetail;
import com.shop.repository.CommentDetailRepository;
import com.shop.services.CommentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CommentDetailServiceImpl implements CommentDetailService {
    @Autowired
    CommentDetailRepository CommentDetailRepository;
    @Override
    public CommentDetail saveComment(CommentDetail commentDetail) {
        return CommentDetailRepository.save(commentDetail);
    }


}
