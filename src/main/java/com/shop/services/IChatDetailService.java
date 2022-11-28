package com.shop.services;

import com.shop.entity.ChatDetail;

import java.util.List;
import java.util.Set;

public interface IChatDetailService {
    ChatDetail createChatDetail(ChatDetail chatDetail);

    Set<ChatDetail> findAll();

    Integer countByCustomer(Long customerId);

    Integer countByAdmin(Long adminId);

    List<ChatDetail> findByCustomerAndIsEnd(Long customerId);

    List<ChatDetail> findByChatUserCustomerAndAdmin(Long customerId, Long userAdminId);

    List<ChatDetail> findByChatID(Long chatId);

    List<ChatDetail> findTop1ByChatId(Long chatId);
}
