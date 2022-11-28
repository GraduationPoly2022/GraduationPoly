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
    private ICommentService iCommentService;
    @Autowired
    private ICommentDetailService commentDTService;

    //add comment
    @PostMapping("/")
    public ResponseEntity<ResponseMessage> handlerCreateComment(@RequestBody CommentDto commentDto) {
        ResponseEntity<ResponseMessage> message = null;
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        Comment commentSave = this.iCommentService.createComment(comment);
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

    @GetMapping("/{productId}/{userId}")
    public ResponseEntity<ResponseMessage> getAllComment(@PathVariable("productId") Long productId,
                                                         @PathVariable Long userId) {
        List<Comment> list = this.iCommentService.findCommentByProducts(productId);
        List<CommentDto> orderDtoList = this.iCommentService.listComment(list, userId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMessage(StatusMessage.OK, "Get all data successful!", orderDtoList)
        );

    }

    //list all comment admin
    @GetMapping("/get-data")
    public ResponseEntity<ResponseMessage> AllCommentAdmin() {
        List<Comment> list = this.iCommentService.findAllComment();
        return transferList(list);
    }


    // delete
    @PatchMapping("/hidden/{commentId}")
    public ResponseEntity<ResponseMessage> unHidden(@PathVariable("commentId") Long commentId) {
        ResponseEntity<ResponseMessage> message;
        Comment commentFindById = this.iCommentService.findByCommentId(commentId);
        if (commentFindById != null) {
            commentFindById.setHidden(!commentFindById.isHidden());
        }
        Comment commentSave = this.iCommentService.createComment(commentFindById);
        message = ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(StatusMessage.OK,
                "Deleted comment is successful!", commentSave));
        return message;
    }


    // method commons
    private ResponseEntity<ResponseMessage> transferList(List<Comment> list) {
        List<CommentDto> orderDtoList = this.iCommentService.listComment(list);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMessage(StatusMessage.OK, "Get all data successful!", orderDtoList)
        );
    }

    // count comment
    @GetMapping("/count/{prodId}")
    private Integer countCommentByProductId(@PathVariable("prodId") Long prodId) {
        return this.iCommentService.countCommentByProductId(prodId);
    }


}
