package com.shop.controller;

import com.shop.dto.LikeDto;
import com.shop.dto.LikeRepDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.LikeComment;
import com.shop.entity.LikeReply;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.ILikeCommentService;
import com.shop.services.ILikeReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/emotion")
public class LikeController {
    @Autowired
    private ILikeCommentService iLikeCommentService;


    @Autowired
    private ILikeReplyService iLikeReplyService;

    // like comment

    @PostMapping("/like")
    public ResponseEntity<ResponseMessage> createLikeRep(@RequestBody LikeDto likeDto) {
        ResponseEntity<ResponseMessage> message;
        LikeComment checkUserCommentId = this.iLikeCommentService.findCommentUser(likeDto.getCmtLk(), likeDto.getUserLk());
        try {
            //create
            if (checkUserCommentId == null) {
                LikeComment likeComment = new LikeComment();
                BeanUtils.copyProperties(likeDto, likeComment, "commentUser");
                LikeComment likeCommentSave = this.iLikeCommentService.saveOrUpdate(likeComment);
                message = ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseMessage(StatusMessage.OK, "Add  like or dislike is successful", likeCommentSave));
            }
            //Delete
            else if (checkUserCommentId.getEmotion() == likeDto.getEmotion()) {
                Optional<LikeComment> delete = this.iLikeCommentService.deleteComment(likeDto.getCmtLk(), likeDto.getUserLk());
                message = ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseMessage(StatusMessage.OK, "Deleted like is successful", delete));
            }
            // update
            else {
                checkUserCommentId.setEmotion(!checkUserCommentId.getEmotion());
                LikeComment update = this.iLikeCommentService.saveOrUpdate(checkUserCommentId);
                message = ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseMessage(StatusMessage.OK, "update like is successful", update));
            }

        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }


    //    Like Reply comment
    @PostMapping("/like-reply")
    public ResponseEntity<ResponseMessage> createLikeRep(@RequestBody LikeRepDto likeRepDto) {
        ResponseEntity<ResponseMessage> message;
        LikeReply checkUserReply = this.iLikeReplyService.findCommentUserReply(likeRepDto.getCmtRep(), likeRepDto.getUserRep());
        try {
            if (checkUserReply == null) {
                LikeReply likeReply = new LikeReply();
                BeanUtils.copyProperties(likeRepDto, likeReply, "commentUserReply");
                LikeReply likeReplySave = this.iLikeReplyService.saveOrUpdateReply(likeReply);
                message = ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseMessage(StatusMessage.OK, "Add like or dislike reply successful", likeReplySave));
            }
            //Delete
            else if (checkUserReply.getEmotion() == likeRepDto.getEmotion()) {
                Optional<LikeReply> deleteReply = this.iLikeReplyService.deleteCommentReply(likeRepDto.getCmtRep(), likeRepDto.getUserRep());
                message = ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseMessage(StatusMessage.OK, "Deleted like reply is successful", deleteReply));
            }
            // update
            else {
                checkUserReply.setEmotion(!checkUserReply.getEmotion());
                LikeReply updateReply = this.iLikeReplyService.saveOrUpdateReply(checkUserReply);
                message = ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseMessage(StatusMessage.OK, "update like reply is successful", updateReply));
            }

        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }

}
