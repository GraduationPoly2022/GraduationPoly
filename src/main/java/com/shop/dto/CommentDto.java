package com.shop.dto;

import com.shop.entity.CommentDetail;
import com.shop.entity.Products;
import com.shop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    List<CommentDetailDto> commentDetails;
    private Long commentId;
    private String content;
    private Boolean hidden = false;
    private Date commentDate;
    private Integer likeComment;
    private Integer disLikeComment;
    private Products prodComment;
    private User userComments;
    private CommentDetail commentDt;
    private Boolean likeOrDislike;

}
