package com.shop.controller;

import com.shop.dto.CommentDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.Comment;
import com.shop.entity.CommentDetail;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.ICommentDetailService;
import com.shop.services.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private ICommentDetailService commentDTService;

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

    @PostMapping("/addComment")
    public ResponseEntity<ResponseMessage> handlerCreateCommentDetail(@RequestBody CommentDto commentDTDto) {
        ResponseEntity<ResponseMessage> message = null;
        CommentDetail commentDetail = new CommentDetail();
        BeanUtils.copyProperties(commentDTDto, commentDetail);
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

    @GetMapping("/")
    public ResponseEntity<ResponseMessage> findAllComment() {
        ResponseEntity<ResponseMessage> message = null;
        List<Comment> list = this.commentService.findAllComment();
        if (list != null) {
            message = ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(StatusMessage.OK, "Get data successful", list));

        } else {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "No data", null));
        }
        return message;
    }

    @GetMapping("/listDetail")
    public ResponseEntity<ResponseMessage> findAllCommentDetail() {
        ResponseEntity<ResponseMessage> message = null;
        List<CommentDetail> listDt = this.commentDTService.findAllCommentDetail();
        if (listDt != null) {
            message = ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(StatusMessage.OK, "Get data detail successful", listDt));

        } else {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "No data", null));
        }
        return message;
    }


}
