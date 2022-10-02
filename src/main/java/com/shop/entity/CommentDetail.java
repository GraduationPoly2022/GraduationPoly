package com.shop.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class CommentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cmdeId;
    private String contentReply;
    @Temporal(TemporalType.DATE)
    private Date contentDateReply;
    @ManyToOne(fetch = FetchType.EAGER)
    private Comment cmde;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userCmde;
    ;

    public CommentDetail() {
    }

    public CommentDetail(Long cmdeId, String contentReply, Date contentDateReply,
                         Comment cmde, User userCmde) {
        this.cmdeId = cmdeId;
        this.contentReply = contentReply;
        this.contentDateReply = contentDateReply;
        this.cmde = cmde;
        this.userCmde = userCmde;
    }

    public Long getCmdeId() {
        return cmdeId;
    }

    public void setCmdeId(Long cmdeId) {
        this.cmdeId = cmdeId;
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


    public Comment getCmde() {
        return cmde;
    }

    public void setCmde(Comment cmde) {
        this.cmde = cmde;
    }

    public User getUserCmde() {
        return userCmde;
    }

    public void setUserCmde(User userCmde) {
        this.userCmde = userCmde;
    }
}
