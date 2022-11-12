package com.shop.controller;

import com.shop.dto.CommentDetailDto;
import com.shop.dto.CommentDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.Comment;
import com.shop.entity.CommentDetail;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.ICommentDetailService;
import com.shop.services.ICommentService;
import com.shop.services.ILikeCommentService;
import com.shop.services.ILikeReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private ICommentDetailService commentDTService;

    @Autowired
    private ILikeCommentService iLikeCommentService;

    @Autowired
    private ILikeReplyService iLikeReplyService;


    //add comment
    @PostMapping("/")
    public ResponseEntity<ResponseMessage> handlerCreateComment(@RequestBody CommentDto commentDto) {
        ResponseEntity<ResponseMessage> message = null;
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        Comment commentSave = this.commentService.createComment(comment);
        try {
            if (commentSave != null) {
                message = ResponseEntity.status(HttpStatus.OK)
                        .body(
                                new ResponseMessage(StatusMessage.OK,
                                        "Insert comment successful",
                                        commentSave)
                        );

            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResponseMessage(StatusMessage.ERROR,
                                    e.getMessage(), null
                            )
                    );
        }
        return message;
    }

    //add comment detail
    @PostMapping("/add-comment")
    public ResponseEntity<ResponseMessage> handlerCreateCommentDetail(@RequestBody CommentDto commentDTDto) {
        ResponseEntity<ResponseMessage> message = null;
        CommentDetail commentDetail = new CommentDetail();
        BeanUtils.copyProperties(commentDTDto.getCommentDt(), commentDetail,
                "commentDtId");

        CommentDetail commentDetailSave = this.commentDTService.addCommentDetail(commentDetail);
        try {
            if (commentDetailSave != null) {
                message = ResponseEntity.status(HttpStatus.OK)
                        .body(
                                new ResponseMessage(StatusMessage.OK,
                                        "Insert comment detail successful",
                                        commentDetailSave)
                        );

            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResponseMessage(StatusMessage.ERROR,
                                    e.getMessage(),
                                    null)
                    );
        }
        return message;
    }

    //  List all comment and Like DisLike Comment,CommentReply
    private List<CommentDto> transfer(List<Comment> comments) {
        List<CommentDto> CommentDtoList = new ArrayList<>();
        if (!comments.isEmpty()) {
            for (Comment comment : comments) {
                CommentDto commentDto = new CommentDto();
                commentDto.setCommentId(comment.getCommentId());
                commentDto.setContent(comment.getContent());
                commentDto.setCommentDate(comment.getCommentDate());
                commentDto.setProdComment(comment.getProdComment());
                commentDto.setUserComments(comment.getUserComments());
                Integer countLikeComment = this.iLikeCommentService.countLike(comment.getCommentId());
                Integer countDisLikeComment = this.iLikeCommentService.countDislike(comment.getCommentId());
                if (countDisLikeComment != null) {
                    commentDto.setDisLikeComment(countDisLikeComment);
                }
                if (countLikeComment != null) {
                    commentDto.setLikeComment(countLikeComment);
                }
                List<CommentDetailDto> commentDetail = this.commentDTService.findCommentDtById(comment.getCommentId());
                commentDto.setCommentDetails(commentDetail);
                CommentDtoList.add(commentDto);
            }
        }
        return CommentDtoList;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseMessage> getAllComment(@PathVariable("productId") Long productId) {
        List<Comment> list = this.commentService.findCommentByProducts(productId);
        return transferList(list);

    }

    //list all comment admin
    @GetMapping("/get-data")
    public ResponseEntity<ResponseMessage> AllCommentAdmin() {
        List<Comment> list = this.commentService.findAllComment();
        return transferList(list);
    }


    //     delete
    @PatchMapping("/hidden/{commentId}")
    public ResponseEntity<ResponseMessage> unHidden(@PathVariable("commentId") Long commentId) {
        ResponseEntity<ResponseMessage> message;
        Comment commentFindById = this.commentService.findByCommentId(commentId);
        if (commentFindById != null) {
            commentFindById.setHidden(!commentFindById.isHidden());
        }
        Comment commentSave = this.commentService.createComment(commentFindById);
        message = ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(StatusMessage.OK,
                "Deleted comment is successful!", commentSave));
        return message;
    }


    //     method chung
    private ResponseEntity<ResponseMessage> transferList(List<Comment> list) {
        List<CommentDto> orderDtoList = transfer(list);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMessage(StatusMessage.OK, "Get all data successful!", orderDtoList)
        );
    }

}
