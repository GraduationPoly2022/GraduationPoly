package com.shop.services.Impl;

import com.shop.dto.CommentDetailDto;
import com.shop.dto.CommentDto;
import com.shop.entity.Comment;
import com.shop.entity.LikeComment;
import com.shop.entity.Products;
import com.shop.repository.CommentRepository;
import com.shop.services.ICommentDetailService;
import com.shop.services.ILikeCommentService;
import com.shop.services.ILikeReplyService;
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
    @Autowired
    private ILikeCommentService iLikeCommentService;

    @Autowired
    private ILikeReplyService iLikeReplyService;

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
    //  List all comment and Like DisLike Comment,CommentReply Admin
    public List<CommentDto> listComment(List<Comment> comments) {
        List<CommentDto> CommentDtoList = new ArrayList<>();
        if (!comments.isEmpty()) {
            for (Comment comment : comments) {
                List<CommentDetailDto> commentDetailList = new ArrayList<>();
                CommentDto commentDto = new CommentDto();
                getCommentDto(commentDto, comment);

                List<CommentDetailDto> commentDetail = this.iCommentDetailService
                        .findCommentDtById(comment.getCommentId());
                getCommentDetailDto(commentDetailList, commentDto, commentDetail);
                CommentDtoList.add(commentDto);
            }
        }
        return CommentDtoList;
    }

    @Override
    //  List all comment and Like DisLike Comment,CommentReply User
    public List<CommentDto> listComment(List<Comment> comments, Long userId) {
        List<CommentDto> CommentDtoList = new ArrayList<>();
        if (!comments.isEmpty()) {
            for (Comment comment : comments) {
                List<CommentDetailDto> commentDetailList = new ArrayList<>();
                CommentDto commentDto = new CommentDto();

                getCommentDto(commentDto, comment);

                List<CommentDetailDto> commentDetail = this.iCommentDetailService
                        .findCommentDtById(comment.getCommentId(), userId);
                getCommentDetailDto(commentDetailList, commentDto, commentDetail);
//                commentDto.setCommentDetails(commentDetail);
                LikeComment likeOrDislike = this.iLikeCommentService.getLikeOrDislike(userId,
                        comment.getCommentId());
                if (likeOrDislike != null) {
                    commentDto.setLikeOrDislike(likeOrDislike.getEmotion());
                }


                CommentDtoList.add(commentDto);
            }
        }
        return CommentDtoList;
    }

    private void getCommentDetailDto(List<CommentDetailDto> commentDetailList, CommentDto commentDto, List<CommentDetailDto> commentDetail) {
        for (CommentDetailDto detail : commentDetail) {
            CommentDetailDto cd = new CommentDetailDto();
            BeanUtils.copyProperties(detail, cd, "cmde");
            Comment c = new Comment();
            c.setCommentId(detail.getCmde().getCommentId());
            cd.setCmde(c);
            commentDetailList.add(cd);
        }
        commentDto.setCommentDetails(commentDetailList);
    }


    private void getCommentDto(CommentDto commentDto, Comment comment) {
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setContent(comment.getContent());
        commentDto.setCommentDate(comment.getCommentDate());
        Products product = new Products();
        product.setProdId(comment.getProdComment().getProdId());
        commentDto.setProdComment(product);
        commentDto.setUserComments(comment.getUserComments());
        Integer countLikeComment = this.iLikeCommentService.countLike(comment.getCommentId());
        Integer countDisLikeComment = this.iLikeCommentService.countDislike(comment.getCommentId());

        if (countDisLikeComment != null) {
            commentDto.setDisLikeComment(countDisLikeComment);
        }
        if (countLikeComment != null) {
            commentDto.setLikeComment(countLikeComment);
        }
    }

    @Override
    public Integer countCommentByProductId(Long productId) {
        return this.commentRepository.countByProdComment_prodId(productId).orElse(null);
    }
}



