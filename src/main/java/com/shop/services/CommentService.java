package com.shop.services;

import com.shop.entity.Comment;

import java.util.List;

public interface CommentService {

//    Comment save(CommentDto commentDto);

    Comment save(Comment comment);

    List<Comment> findAllComment();

//    List<Comment> GetAll();


}
