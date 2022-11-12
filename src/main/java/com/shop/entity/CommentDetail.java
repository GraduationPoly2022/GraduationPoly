package com.shop.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class CommentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cmdeId;
    private String contentReply;
    @Temporal(TemporalType.TIMESTAMP)
    private Date contentDateReply;
    @ManyToOne(fetch = FetchType.EAGER)
    private Comment cmde;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userCmde;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User userReply;

    @OneToMany(mappedBy = "cmtRep", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<LikeReply> likeRep;


    public CommentDetail() {
    }

    public CommentDetail(Long cmdeId, String contentReply, Date contentDateReply,
                         Comment cmde, User userCmde, User userReply) {
        this.cmdeId = cmdeId;
        this.contentReply = contentReply;
        this.contentDateReply = contentDateReply;
        this.cmde = cmde;
        this.userCmde = userCmde;
        this.userReply = userReply;
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

    public User getUserReply() {
        return userReply;
    }

    public void setUserReply(User userReply) {
        this.userReply = userReply;
    }

    public Set<LikeReply> getLikeRep() {
        return likeRep;
    }

    public void setLikeRep(Set<LikeReply> likeRep) {
        this.likeRep = likeRep;
    }
}
