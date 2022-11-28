package com.shop.repository;

import com.shop.entity.ChatDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatDetailRepository extends JpaRepository<ChatDetail, Long> {
    Integer countByChat_userCustomer_userIdAndIsRead(Long chat_userCustomer_userId, Boolean isRead);

    List<ChatDetail> findByChat_UserCustomer_UserIdAndIsReadOrderByTimeChatDesc(Long userId, Boolean isRead);

    List<ChatDetail> findTop1ByChat_chatIdAndManChatOrderByTimeChatDesc(Long chat_chatId, Boolean manChat);

    List<ChatDetail> findByChat_ChatId(Long chat_chatId);

    List<ChatDetail> findByChat_UserAdmin_userIdAndChat_UserCustomer_userIdAndChat_isEndAndChat_isApply(Long chat_applyChat_userAdmin_userId, Long chat_userCustomer_userId, Boolean chat_isEnd, Boolean chat_isApply);
}