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



    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Comment comment_commentDetails;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user_commentDetails;;


    public CommentDetail() {
    }

    public CommentDetail(Long commentDtId, String contentReply, Date contentDateReply, Comment comment_commentDetails, User user_commentDetails) {
        CommentDtId = commentDtId;
        ContentReply = contentReply;
        ContentDateReply = contentDateReply;
        this.comment_commentDetails = comment_commentDetails;
        this.user_commentDetails = user_commentDetails;
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



    public Comment getComment_commentDetails() {
        return comment_commentDetails;
    }

    public void setComment_commentDetails(Comment comment_commentDetails) {
        this.comment_commentDetails = comment_commentDetails;
    }

    public User getUser_commentDetails() {
        return user_commentDetails;
    }

    public void setUser_commentDetails(User user_commentDetails) {
        this.user_commentDetails = user_commentDetails;
    }
}
