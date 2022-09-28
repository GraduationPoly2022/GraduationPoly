package com.shop.controller;


import com.shop.dto.CommentDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.Comment;
import com.shop.entity.CommentDetail;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.CommentDetailService;
import com.shop.services.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentDetailService commentDetailService;


    @GetMapping("/list")
    public ResponseEntity<ResponseMessage> findAllComments() {
        return ResponseEntity
                .ok(new ResponseMessage(
                        StatusMessage.OK, "Get data from db successfully", this.commentService.findAllComment())
                );
    }

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> CreateComment(@RequestBody CommentDto commentDto) {
        ResponseEntity<ResponseMessage> message = null;
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        Comment commentSave = this.commentService.save(comment);
        try {
            if (commentSave != null) {
                message = ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(StatusMessage.OK, "Create Comment Successful", commentSave));
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "Create Comment Unsuccessful", null));
        }
        return message;
    }

    @PostMapping("/detail")
    public ResponseEntity<ResponseMessage> CreateCommentDetail(@RequestBody CommentDto commentDto) {
        ResponseEntity<ResponseMessage> message = null;
        CommentDetail commentDetail = new CommentDetail();
        BeanUtils.copyProperties(commentDto, commentDetail);
        CommentDetail CommentDetailSave = this.commentDetailService.saveComment(commentDetail);
        try {
            if (CommentDetailSave != null) {
                message = ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(StatusMessage.OK, "Create CommentDetail Successful", CommentDetailSave));

            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "Create CommentDetail Unsuccesful", null));
        }
        return message;
    }


}
