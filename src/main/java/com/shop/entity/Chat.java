package com.shop.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatId;

    private Boolean isApply;

    private Date timeSend;

    private Boolean isEnd;
    @ManyToOne(fetch = FetchType.EAGER)
    private User userCustomer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat")
    private Set<ChatDetail> chatDetailSet = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "user_admin_user_id")
    private User userAdmin;

    public Chat() {
    }


    public Chat(Long chatId, Boolean isApply, Date timeSend, Boolean isEnd, User userCustomer, User userAdmin) {
        this.chatId = chatId;
        this.isApply = isApply;
        this.timeSend = timeSend;
        this.isEnd = isEnd;
        this.userCustomer = userCustomer;
        this.userAdmin = userAdmin;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Boolean getIsApply() {
        return isApply;
    }

    public void setIsApply(Boolean isApply) {
        this.isApply = isApply;
    }

    public Date getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(Date timeSend) {
        this.timeSend = timeSend;
    }

    public User getUserCustomer() {
        return userCustomer;
    }

    public void setUserCustomer(User userCustomer) {
        this.userCustomer = userCustomer;
    }

    public Set<ChatDetail> getChatDetailSet() {
        return chatDetailSet;
    }

    public void setChatDetailSet(Set<ChatDetail> chatDetailSet) {
        this.chatDetailSet = chatDetailSet;
    }

    public Boolean getApply() {
        return isApply;
    }

    public void isApply(Boolean apply) {
        isApply = apply;
    }

    public Boolean getEnd() {
        return isEnd;
    }

    public void isEnd(Boolean end) {
        isEnd = end;
    }

    public User getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(User userAdmin) {
        this.userAdmin = userAdmin;
    }
}
