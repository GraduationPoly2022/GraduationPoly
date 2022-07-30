package com.shop.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long CommentId;
    private String Content;
    private Date CommentDate;
    private Long UserId;
    private Long ProductId;


    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CommentDetail> commentDetails;

    @ManyToOne
    @JoinColumn(name = "user_UserId")
    private User users;


    public Comment() {
    }

    public Comment(Long commentId, String content, Date commentDate, Long userId, Long productId) {
        CommentId = commentId;
        Content = content;
        CommentDate = commentDate;
        UserId = userId;
        ProductId = productId;
    }

    public Long getCommentId() {
        return CommentId;
    }

    public void setCommentId(Long commentId) {
        CommentId = commentId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getCommentDate() {
        return CommentDate;
    }

    public void setCommentDate(Date commentDate) {
        CommentDate = commentDate;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }
}
