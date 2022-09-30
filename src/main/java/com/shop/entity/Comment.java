package com.shop.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private String content;
    @Temporal(TemporalType.DATE)
    private Date commentDate;

    @OneToMany(mappedBy = "commentDetails")
    @JsonIgnore
    private Set<CommentDetail> commentDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    private Products productComment;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userComments;

    public Comment() {
    }

    public Comment(Long commentId, String content, Date commentDate, Products productComment, User userComments) {
        this.commentId = commentId;
        this.content = content;
        this.commentDate = commentDate;
        this.commentDetails = commentDetails;
        this.productComment = productComment;
        this.userComments = userComments;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Set<CommentDetail> getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(Set<CommentDetail> commentDetails) {
        this.commentDetails = commentDetails;
    }

    public Products getProductComment() {
        return productComment;
    }

    public void setProductComment(Products productComment) {
        this.productComment = productComment;
    }

    public User getUserComments() {
        return userComments;
    }

    public void setUserComments(User userComments) {
        this.userComments = userComments;
    }
}
