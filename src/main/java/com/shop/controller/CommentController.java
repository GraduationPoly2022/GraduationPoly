package com.shop.controller;

import com.shop.dto.CommentDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.Comment;
import com.shop.entity.CommentDetail;
import com.shop.enumEntity.RoleName;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.ICommentDetailService;
import com.shop.services.ICommentService;
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


    private List<CommentDto> transfer(Long productId) {
        List<CommentDto> CommentDtoList = new ArrayList<>();
        List<Comment> comments = this.commentService.findCommentByProducts(productId);
        if (!comments.isEmpty()) {
            for (Comment comment : comments) {
                CommentDto commentDto = new CommentDto();
                commentDto.setCommentId(comment.getCommentId());
                commentDto.setContent(comment.getContent());
                commentDto.setCommentDate(comment.getCommentDate());
                commentDto.setProdComment(comment.getProdComment());
                commentDto.setUserComments(comment.getUserComments());
                List<CommentDetail> CommentDetail = this.commentDTService.findCommentDtById(comment.getCommentId());
                commentDto.setCommentDetails(CommentDetail);
                CommentDtoList.add(commentDto);
            }
        }
        return CommentDtoList;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseMessage> getAllComment(@PathVariable("productId") Long productId) {
        ResponseEntity<ResponseMessage> message = null;
        List<Comment> list = this.commentService.findCommentByProducts(productId);
        return getResponseMessageResponseEntity(list);

    }


    @GetMapping("/get-data")
    public ResponseEntity<ResponseMessage> AllCommentAdmin() {
        ResponseEntity<ResponseMessage> message = null;
        List<Comment> list = this.commentService.findAllComment();
        return getResponseMessageResponseEntity(list);
    }

    private List<CommentDto> transfer() {
        List<CommentDto> CommentDtoLists = new ArrayList<>();
        List<Comment> comments = this.commentService.findAllComment();
        if (!comments.isEmpty()) {
            for (Comment comment : comments) {
                CommentDto commentDto = new CommentDto();
                commentDto.setCommentId(comment.getCommentId());
                commentDto.setContent(comment.getContent());
                commentDto.setCommentDate(comment.getCommentDate());
                commentDto.setProdComment(comment.getProdComment());
                commentDto.setUserComments(comment.getUserComments());
                List<CommentDetail> CommentDetail = this.commentDTService.findCommentDtById(comment.getCommentId());
                commentDto.setCommentDetails(CommentDetail);
                CommentDtoLists.add(commentDto);
            }
        }
        return CommentDtoLists;
    }

    @PatchMapping("/hidden/{commentId}")
    public ResponseEntity<ResponseMessage> unHidden(@PathVariable("commentId") Long commentId, RoleName roleName) {
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

    private ResponseEntity<ResponseMessage> getResponseMessageResponseEntity(List<Comment> list) {
        ResponseEntity<ResponseMessage> message;
        List<CommentDto> orderDtoList = transfer();
        if (!list.isEmpty()) {
            message = ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(StatusMessage.OK, "Get all data successful!", orderDtoList));
        } else {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.NOT_FOUND, "Not found data", null));
        }
        return message;
    }

}
