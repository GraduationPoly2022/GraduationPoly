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
    private List<CommentDetail> commentDetails;
    private Long commentId;
    private String Content;
    private Date CommentDate;
    private Products productComment;
    private User userComments;
    private CommentDetail commentDt;
    

}
