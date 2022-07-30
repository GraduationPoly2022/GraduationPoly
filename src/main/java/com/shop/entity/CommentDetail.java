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

    public CommentDetail(Long commentDtId, String contentReply, Date contentDateReply, Long commentId, Long userId, Comment comment_commendetails, User user_commentdetails) {
        CommentDtId = commentDtId;
        ContentReply = contentReply;
        ContentDateReply = contentDateReply;
        CommentId = commentId;
        UserId = userId;
        this.comment_commendetails = comment_commendetails;
        this.user_commentdetails = user_commentdetails;
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

    public Comment getComment_commendetails() {
        return comment_commendetails;
    }

    public void setComment_commendetails(Comment comment_commendetails) {
        this.comment_commendetails = comment_commendetails;
    }

    public User getUser_commentdetails() {
        return user_commentdetails;
    }

    public void setUser_commentdetails(User user_commentdetails) {
        this.user_commentdetails = user_commentdetails;
    }
}
