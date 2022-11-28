package com.shop.services;

import com.shop.dto.CommentDto;
import com.shop.entity.Comment;

import java.util.List;

public interface ICommentService {
    Comment createComment(Comment comment);

    List<Comment> findCommentByProducts(Long prodId);

    List<Comment> findAllComment();

    Comment findByCommentId(Long commentId);

    //  List all comment and Like DisLike Comment,CommentReply Admin
    List<CommentDto> listComment(List<Comment> comments);

    List<CommentDto> listComment(List<Comment> comments, Long userId);

    Integer countCommentByProductId(Long productId);
}
