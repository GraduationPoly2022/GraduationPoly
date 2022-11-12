package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class LikeComment {

    @EmbeddedId
    private CommentUser commentUser = new CommentUser();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("commentId")
    @JoinColumn(name = "comment_id")
    @JsonIgnore
    private Comment cmtLk;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userLk;

    private Boolean emotion;

    public LikeComment() {
    }

    public LikeComment(CommentUser commentUser, Comment cmtLk, User userLk, Boolean emotion) {
        this.commentUser = new CommentUser(cmtLk.getCommentId(), userLk.getUserId());
        ;
        this.cmtLk = cmtLk;
        this.userLk = userLk;
        this.emotion = emotion;
    }

    public CommentUser getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(CommentUser commentUser) {
        this.commentUser = commentUser;
    }

    public Comment getCmtLk() {
        return cmtLk;
    }

    public void setCmtLk(Comment cmtLk) {
        this.cmtLk = cmtLk;
    }

    public User getUserLk() {
        return userLk;
    }

    public void setUserLk(User userLk) {
        this.userLk = userLk;
    }

    public Boolean getEmotion() {
        return emotion;
    }

    public void setEmotion(Boolean emotion) {
        this.emotion = emotion;
    }
}
