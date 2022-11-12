package com.shop.services.Impl;

import com.shop.dto.CommentDto;
import com.shop.entity.Comment;
import com.shop.entity.CommentDetail;
import com.shop.entity.Products;
import com.shop.repository.CommentRepository;
import com.shop.services.ICommentDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements com.shop.services.ICommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ICommentDetailService iCommentDetailService;

    @Override
    public Comment createComment(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public List<Comment> findCommentByProducts(Long prodId) {
        return this.commentRepository.findByProdComment_prodId(prodId);
    }

    @Override
    public List<Comment> findAllComment() {
        return this.commentRepository.findAll();
    }

    public Comment findByCommentId(Long commentId) {
        return this.commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public List<CommentDto> listComment(List<Comment> comments) {
        List<CommentDto> CommentDtoList = new ArrayList<>();
        if (!comments.isEmpty()) {
            for (Comment comment : comments) {
                List<CommentDetail> commentDetailList = new ArrayList<>();
                CommentDto commentDto = new CommentDto();
                commentDto.setCommentId(comment.getCommentId());
                commentDto.setContent(comment.getContent());
                commentDto.setCommentDate(comment.getCommentDate());
                Products product = new Products();
                product.setProdId(comment.getProdComment().getProdId());
                commentDto.setProdComment(product);
                commentDto.setUserComments(comment.getUserComments());
                List<CommentDetail> commentDetail = this.iCommentDetailService.findCommentDtById(comment.getCommentId());
                for (CommentDetail detail : commentDetail) {
                    CommentDetail cd = new CommentDetail();
                    BeanUtils.copyProperties(detail, cd, "cmde");
                    Comment c = new Comment();
                    c.setCommentId(detail.getCmde().getCommentId());
                    cd.setCmde(c);
                    commentDetailList.add(cd);
                }
                commentDto.setCommentDetails(commentDetailList);
                CommentDtoList.add(commentDto);
            }
        }
        return CommentDtoList;
    }
}



