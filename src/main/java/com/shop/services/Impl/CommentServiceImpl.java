package com.shop.services.Impl;

import com.shop.entity.Comment;
import com.shop.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements com.shop.services.ICommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public List<Comment> findCommentByProducts(Long productId) {
        return this.commentRepository.findByProductComment_productId(productId);
    }


}



