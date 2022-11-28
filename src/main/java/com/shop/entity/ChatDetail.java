package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class ChatDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatDetailId;
    @Column(columnDefinition = "varchar(500)")
    private String content;

    private String image;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeChat;

    private Boolean manChat;

    @ManyToOne
    @JsonIgnore
    private Chat chat;

    private Boolean isRead = false;

    public ChatDetail() {
    }

    public ChatDetail(Long chatDetailId, String content, String image, Date timeChat, Boolean manChat, Chat chat, Boolean isRead) {
        this.chatDetailId = chatDetailId;
        this.content = content;
        this.image = image;
        this.timeChat = timeChat;
        this.manChat = manChat;
        this.chat = chat;
        this.isRead = isRead;
    }

    public Long getChatDetailId() {
        return chatDetailId;
    }

    public void setChatDetailId(Long chatDetailId) {
        this.chatDetailId = chatDetailId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getTimeChat() {
        return timeChat;
    }

    public void setTimeChat(Date timeChat) {
        this.timeChat = timeChat;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatDetail that = (ChatDetail) o;
        return Objects.equals(chatDetailId, that.chatDetailId)
                && Objects.equals(content, that.content)
                && Objects.equals(image, that.image)
                && Objects.equals(timeChat, that.timeChat)
                && Objects.equals(chat, that.chat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatDetailId, content, image, timeChat, chat);
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Boolean getManChat() {
        return manChat;
    }

    public void setManChat(Boolean manChat) {
        this.manChat = manChat;
    }
}
