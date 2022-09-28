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

    @OneToMany(mappedBy = "commentDetails")
    @JsonIgnore
    private Set<CommentDetail> commentDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    private Products productComment;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userComments;

    public Comment() {
    }

    public Comment(Long commentId, String content, Date commentDate,
                   Products productComment, User userComments) {
        CommentId = commentId;
        Content = content;
        CommentDate = commentDate;
        this.productComment = productComment;
        this.userComments = userComments;
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

    public User getUserComments() {
        return userComments;
    }

    public void setUserComments(User userComments) {
        this.userComments = userComments;
    }

    public Products getProductComment() {
        return productComment;
    }

    public void setProductComment(Products productComment) {
        this.productComment = productComment;
    }
}
