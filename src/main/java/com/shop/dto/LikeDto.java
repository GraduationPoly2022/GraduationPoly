package com.shop.dto;

import com.shop.entity.Comment;
import com.shop.entity.CommentUser;
import com.shop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {
    private CommentUser commentUser;
    private Boolean emotion;
    private Comment cmtLk;
    private User userLk;
}
