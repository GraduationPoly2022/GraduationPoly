package com.shop.dto;

import com.shop.entity.Comment;
import com.shop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetailDto {
    private Long cmdeId;
    private String contentReply;
    private Date contentDateReply;
    private Integer likeReply;
    private Integer disLikeReply;
    private Comment cmde;
    private User userCmde;
    private User userReply;

}
