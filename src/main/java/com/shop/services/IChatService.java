package com.shop.services;

import com.shop.dto.ChatDto;
import com.shop.entity.Chat;

import java.util.List;

public interface IChatService {
    Chat createChat(Chat chat);

    List<ChatDto> findByUserCustomer(Long customerId);

    List<ChatDto> findByAdminAndIsApply(Long adminId);

    Integer countNotifiOfUserAdmins(Long adminId);

    Integer countNotifiOfUserCustomers(Long customerId);

    ChatDto findById(Long chatId);

    ChatDto findByIdAndCustomer(Long chatId, Long customerId);

    List<ChatDto> findAll();

    Chat findByIdAndIsApply(Long chatId);

    List<ChatDto> findAllByPublic(Long customerId);

    Chat findByCustomerAndIsEnd(Long customerId);

    Integer countByApply();
}
