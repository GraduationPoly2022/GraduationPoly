package com.shop.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CommentUserReply implements Serializable {
    @Column(name = "cmde_id")
    private Long cmdeId;

    @Column(name = "user_cmde_user_id")
    private Long userCmde;

    public CommentUserReply() {
    }

    public CommentUserReply(Long cmdeId, Long userCmde) {
        this.cmdeId = cmdeId;
        this.userCmde = userCmde;
    }

    public Long getCmdeId() {
        return cmdeId;
    }

    public void setCmdeId(Long cmdeId) {
        this.cmdeId = cmdeId;
    }

    public Long getUserCmde() {
        return userCmde;
    }

    public void setUserCmde(Long userCmde) {
        this.userCmde = userCmde;
    }
}
