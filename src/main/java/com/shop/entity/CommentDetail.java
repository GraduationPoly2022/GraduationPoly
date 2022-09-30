package com.shop.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class CommentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentDtId;
    private String contentReply;
    @Temporal(TemporalType.DATE)
    private Date contentDateReply;
    @ManyToOne(fetch = FetchType.EAGER)
    private Comment commentDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userCommentDetails;
    ;

    public CommentDetail() {
    }

    public CommentDetail(Long commentDtId, String contentReply, Date contentDateReply, Comment commentDetails, User userCommentDetails) {
        this.commentDtId = commentDtId;
        this.contentReply = contentReply;
        this.contentDateReply = contentDateReply;
        this.commentDetails = commentDetails;
        this.userCommentDetails = userCommentDetails;
    }

    public Long getCommentDtId() {
        return commentDtId;
    }

    public void setCommentDtId(Long commentDtId) {
        this.commentDtId = commentDtId;
    }

    public String getContentReply() {
        return contentReply;
    }

    public void setContentReply(String contentReply) {
        this.contentReply = contentReply;
    }

    public Date getContentDateReply() {
        return contentDateReply;
    }

    public void setContentDateReply(Date contentDateReply) {
        this.contentDateReply = contentDateReply;
    }

    public Comment getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(Comment commentDetails) {
        this.commentDetails = commentDetails;
    }

    public User getUserCommentDetails() {
        return userCommentDetails;
    }

    public void setUserCommentDetails(User userCommentDetails) {
        this.userCommentDetails = userCommentDetails;
    }
}
