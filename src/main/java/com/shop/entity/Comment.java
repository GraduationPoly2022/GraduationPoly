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


    @OneToMany(mappedBy = "comment_commendetails", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CommentDetail> commentDetails;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user_comments;


    public Comment() {
    }

    public Comment(Long commentId, String content, Date commentDate, Long userId, Long productId, Set<CommentDetail> commentDetails, User user_comments) {
        CommentId = commentId;
        Content = content;
        CommentDate = commentDate;
        UserId = userId;
        ProductId = productId;
        this.commentDetails = commentDetails;
        this.user_comments = user_comments;
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

    public Set<CommentDetail> getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(Set<CommentDetail> commentDetails) {
        this.commentDetails = commentDetails;
    }

    public User getUser_comments() {
        return user_comments;
    }

    public void setUser_comments(User user_comments) {
        this.user_comments = user_comments;
    }
}
