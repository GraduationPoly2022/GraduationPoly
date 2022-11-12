package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class LikeReply {

    @EmbeddedId
    private CommentUserReply commentUserReply = new CommentUserReply();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cmdeId")
    @JoinColumn(name = "cmde_id")
    @JsonIgnore
    private CommentDetail cmtRep;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userCmde")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userRep;

    private Boolean emotion;

    public LikeReply() {
    }

    public LikeReply(CommentUserReply commentUserReply, CommentDetail cmtRep, User userRep, Boolean emotion) {
        this.commentUserReply = new CommentUserReply(cmtRep.getCmdeId(), userRep.getUserId());
        this.cmtRep = cmtRep;
        this.userRep = userRep;
        this.emotion = emotion;
    }

    public CommentUserReply getCommentUserReply() {
        return commentUserReply;
    }

    public void setCommentUserReply(CommentUserReply commentUserReply) {
        this.commentUserReply = commentUserReply;
    }

    public CommentDetail getCmtRep() {
        return cmtRep;
    }

    public void setCmtRep(CommentDetail cmtRep) {
        this.cmtRep = cmtRep;
    }

    public User getUserRep() {
        return userRep;
    }

    public void setUserRep(User userRep) {
        this.userRep = userRep;
    }

    public Boolean getEmotion() {
        return emotion;
    }

    public void setEmotion(Boolean emotion) {
        this.emotion = emotion;
    }

}
