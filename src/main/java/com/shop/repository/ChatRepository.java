package com.shop.repository;

import com.shop.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Integer countByIsApply(Boolean isApply);

    List<Chat> findByUserCustomer_userId(Long userCustomer_userId);

    List<Chat> findByIsApplyAndIsEnd(Boolean isApply, Boolean isEnd);

    Optional<Chat> findByChatIdAndUserCustomer_userId(Long chatId, Long userCustomer_userId);

    List<Chat> findByUserAdmin_userIdAndIsApply(Long userAdmin_userId, Boolean isApply);

    Optional<Chat> findByUserCustomer_userIdAndIsApplyAndChatId(Long userCustomer_userId, Boolean isApply, Long chatId);

    List<Chat> findByUserCustomer_userIdAndIsEnd(Long customerId, Boolean isEnd);

    Optional<Chat> findByChatIdAndIsApply(Long chatId, Boolean isApply);

    Optional<Chat> findByIsEndAndUserCustomer_userId(Boolean isApply, Long userCustomer_userId);

}