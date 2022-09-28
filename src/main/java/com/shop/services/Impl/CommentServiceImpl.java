package com.shop.services.Impl;

import com.shop.entity.Comment;
import com.shop.repository.CommentRepository;
import com.shop.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }

//    @Override
//    public List<Comment> GetAll() {
//        return commentRepository.findAll();
//    }
}
