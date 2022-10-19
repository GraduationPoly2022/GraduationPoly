package com.shop.services;

import com.shop.entity.Comment;

import java.util.List;

public interface ICommentService {
    Comment createComment(Comment comment);

    List<Comment> findCommentByProducts(Long prodId);

    List<Comment> findAllComment();

    Comment findByCommentId(Long commentId);
}