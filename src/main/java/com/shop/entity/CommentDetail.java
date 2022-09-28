package com.shop.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class CommentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long CommentDtId;
    private String ContentReply;
    @Temporal(TemporalType.DATE)
    private Date ContentDateReply;
    @ManyToOne(fetch = FetchType.EAGER)
    private Comment commentDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userCommentDetails;
    ;

    public CommentDetail() {
    }

    public CommentDetail(Long commentDtId, String contentReply, Date contentDateReply,
                         Comment commentDetails, User userCommentDetails) {
        CommentDtId = commentDtId;
        ContentReply = contentReply;
        ContentDateReply = contentDateReply;
        this.commentDetails = commentDetails;
        this.userCommentDetails = userCommentDetails;
    }

    public Long getCommentDtId() {
        return CommentDtId;
    }

    public void setCommentDtId(Long commentDtId) {
        CommentDtId = commentDtId;
    }

    public String getContentReply() {
        return ContentReply;
    }

    public void setContentReply(String contentReply) {
        ContentReply = contentReply;
    }

    public Date getContentDateReply() {
        return ContentDateReply;
    }

    public void setContentDateReply(Date contentDateReply) {
        ContentDateReply = contentDateReply;
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
