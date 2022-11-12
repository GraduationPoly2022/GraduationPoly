package com.shop.dto;

import com.shop.entity.CommentDetail;
import com.shop.entity.CommentUserReply;
import com.shop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeRepDto {
    private Boolean emotion;
    private CommentUserReply commentUserReply;
    private CommentDetail cmtRep;
    private User userRep;
}
