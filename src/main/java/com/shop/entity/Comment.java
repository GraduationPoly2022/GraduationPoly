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

    private Boolean hidden;
    @Temporal(TemporalType.DATE)
    private Date commentDate;

    @OneToMany(mappedBy = "cmde")
    @JsonIgnore
    private Set<CommentDetail> commentDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    private Products prodComment;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userComments;

    public Comment() {
    }


    public Comment(Long commentId, String content, Boolean hidden, Date commentDate,
                   Products prodComment, User userComments) {
        this.commentId = commentId;
        this.content = content;
        this.hidden = hidden;
        this.commentDate = commentDate;
        this.prodComment = prodComment;
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

    public User getUserComments() {
        return userComments;
    }

    public void setUserComments(User userComments) {
        this.userComments = userComments;
    }

    public Products getProdComment() {
        return prodComment;
    }

    public void setProdComment(Products prodComment) {
        this.prodComment = prodComment;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
