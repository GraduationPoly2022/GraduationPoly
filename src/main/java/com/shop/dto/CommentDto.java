package com.shop.dto;

import com.shop.entity.Comment;
import com.shop.entity.Products;
import com.shop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long CommentId;
    private String Content;
    private Date CommentDate;
    private Products productComment;
    private User userComments;
    private Long commentDtId;
    private String contentReply;
    private Date contentDateReply;
    private Comment commentDetails;
    private User userCommentDetails;

}
