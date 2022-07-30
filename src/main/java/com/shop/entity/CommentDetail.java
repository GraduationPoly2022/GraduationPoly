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
    private Long CommentId;
    private Long UserId;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Comment comment_commendetails;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user_commentdetails;;


    public CommentDetail() {
    }

    public CommentDetail(Long commentDtId, String contentReply, Date contentDateReply, Long commentId, Long userId) {
        CommentDtId = commentDtId;
        ContentReply = contentReply;
        ContentDateReply = contentDateReply;
        CommentId = commentId;
        UserId = userId;
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

    public Long getCommentId() {
        return CommentId;
    }

    public void setCommentId(Long commentId) {
        CommentId = commentId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }
}
