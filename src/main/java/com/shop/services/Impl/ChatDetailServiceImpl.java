package com.shop.services.Impl;

import com.shop.entity.ChatDetail;
import com.shop.repository.ChatDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ChatDetailServiceImpl implements com.shop.services.IChatDetailService {
    @Autowired
    private ChatDetailRepository chatDetailRepository;

    @Override
    public ChatDetail createChatDetail(ChatDetail chatDetail) {
        return this.chatDetailRepository.save(chatDetail);
    }

    @Override
    public Set<ChatDetail> findAll() {
        return new HashSet<>(this.chatDetailRepository.findAll());
    }

    @Override
    public Integer countByCustomer(Long customerId) {
        return this.chatDetailRepository.countByChat_userCustomer_userIdAndIsRead(customerId, true);
    }

    @Override
    public Integer countByAdmin(Long adminId) {
        return this.chatDetailRepository.countByChat_userCustomer_userIdAndIsRead(adminId, true);
    }

    @Override
    public List<ChatDetail> findByCustomerAndIsEnd(Long customerId) {
        return this.chatDetailRepository.findByChat_UserCustomer_UserIdAndIsReadOrderByTimeChatDesc(customerId, false);
    }

    @Override
    public List<ChatDetail> findByChatUserCustomerAndAdmin(Long customerId, Long userAdminId) {
        return this.chatDetailRepository
                .findByChat_UserAdmin_userIdAndChat_UserCustomer_userIdAndChat_isEndAndChat_isApply(userAdminId, customerId, false, true);
    }

    @Override
    public List<ChatDetail> findByChatID(Long chatId) {
        return this.chatDetailRepository.findByChat_ChatId(chatId);
    }

    @Override
    public List<ChatDetail> findTop1ByChatId(Long chatId) {
        return this.chatDetailRepository.findTop1ByChat_chatIdAndManChatOrderByTimeChatDesc(chatId, false);
    }

}
