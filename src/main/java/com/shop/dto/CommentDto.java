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
    List<CommentDetail> commentDT;
    private Long CommentId;
    private String Content;
    private Date CommentDate;
    private User userComments;
    private Products productComment;
}
