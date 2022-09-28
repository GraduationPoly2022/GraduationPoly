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
    @Temporal(TemporalType.DATE)
    private Date CommentDate;

    @OneToMany(mappedBy = "comment_commentDetails", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CommentDetail> commentDetails;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Products product_comment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user_comments;

    public Comment() {
    }

    public Comment(Long commentId, String content, Date commentDate,
                   Products product_comment, User user_comments) {
        CommentId = commentId;
        Content = content;
        CommentDate = commentDate;
        this.product_comment = product_comment;
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

    public Products getProduct_comment() {
        return product_comment;
    }

    public void setProduct_comment(Products product_comment) {
        this.product_comment = product_comment;
    }
}
