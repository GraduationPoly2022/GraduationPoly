package com.shop.services.Impl;

import com.shop.dto.ChatDto;
import com.shop.entity.Chat;
import com.shop.repository.ChatRepository;
import com.shop.services.IChatDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements com.shop.services.IChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private IChatDetailService chatDetailService;

    @Override
    public Chat createChat(Chat chat) {
        return this.chatRepository.save(chat);
    }


    @Override
    public List<ChatDto> findByUserCustomer(Long customerId) {
        List<ChatDto> chatDtoList = new ArrayList<>();
        this.convertChatToDto(chatDtoList, this.chatRepository.findByUserCustomer_userId(customerId));
        return chatDtoList;
    }

    @Override
    public List<ChatDto> findByAdminAndIsApply(Long adminId) {
        List<ChatDto> chatDtoList = new ArrayList<>();
        this.chatToDto(chatDtoList, this.chatRepository.findByUserAdmin_userIdAndIsApply(adminId, true));
        return chatDtoList;
    }

    @Override
    public Integer countNotifiOfUserAdmins(Long adminId) {
        return this.chatDetailService.countByAdmin(adminId);
    }

    @Override
    public Integer countNotifiOfUserCustomers(Long customerId) {
        return this.chatDetailService.countByCustomer(customerId);
    }

    @Override
    public ChatDto findById(Long chatId) {
        ChatDto chatDto = new ChatDto();
        Optional<Chat> optionalChat = this.chatRepository.findById(chatId);
        if (optionalChat.isPresent()) {
            Chat chat = optionalChat.get();
            chatDto.setChatId(chat.getChatId());
            chatDto.setIsApply(chat.getIsApply());
            chatDto.setUserAdmin(chat.getUserAdmin());
            chatDto.setUserCustomer(chat.getUserCustomer());
            chatDto.setChatDetailSet(new HashSet<>(this.chatDetailService.findByChatID(chat.getChatId())));
        }
        return chatDto;
    }

    @Override
    public ChatDto findByIdAndCustomer(Long chatId, Long customerId) {
        ChatDto chatDto = new ChatDto();
        Optional<Chat> optionalChat = this.chatRepository.findByChatIdAndUserCustomer_userId(chatId, customerId);
        if (optionalChat.isPresent()) {
            Chat chat = optionalChat.get();
            chatDto.setChatId(chat.getChatId());
            chatDto.setIsApply(chat.getIsApply());
            chatDto.setUserAdmin(chat.getUserAdmin());
            chatDto.setUserCustomer(chat.getUserCustomer());
            chatDto.setChatDetailSet(this.chatDetailService.findAll());
        }
        return chatDto;
    }

    @Override
    public List<ChatDto> findAll() {
        List<ChatDto> chatDtoList = new ArrayList<>();
        this.chatToDto(chatDtoList, this.chatRepository.findByIsApplyAndIsEnd(false, false));
        return chatDtoList;
    }

    @Override
    public Chat findByIdAndIsApply(Long chatId) {
        return this.chatRepository.findByChatIdAndIsApply(chatId, false).orElse(null);
    }

    @Override
    public List<ChatDto> findAllByPublic(Long customerId) {
        List<ChatDto> chatDtoList = new ArrayList<>();
        List<Chat> chatList = this.chatRepository.findByUserCustomer_userIdAndIsEnd(customerId, false);
        for (Chat chat : chatList) {
            ChatDto chatDto = new ChatDto();
            chatDto.setChatId(chat.getChatId());
            chatDto.setIsApply(chat.getIsApply());
            chatDto.setUserAdmin(chat.getUserAdmin());
            chatDto.setUserCustomer(chat.getUserCustomer());
            chatDto.setChatDetailSet(new HashSet<>(this.chatDetailService.findByCustomerAndIsEnd(chat.getUserCustomer().getUserId())));
            chatDtoList.add(chatDto);
        }
        return chatDtoList;
    }

    @Override
    public Chat findByCustomerAndIsEnd(Long customerId) {
        return this.chatRepository.findByIsEndAndUserCustomer_userId(false, customerId).orElse(null);
    }

    @Override
    public Integer countByApply() {
        return this.chatRepository.countByIsApply(false);
    }

    private void convertChatToDto(List<ChatDto> chatDtoList, List<Chat> chatList) {
        for (Chat chat : chatList) {
            ChatDto chatDto = new ChatDto();
            chatDto.setChatId(chat.getChatId());
            chatDto.setIsApply(chat.getIsApply());
            chatDto.setUserAdmin(chatDto.getUserAdmin());
            chatDto.setUserCustomer(chat.getUserCustomer());
            chatDto.setChatDetailSet(this.chatDetailService.findAll());
            chatDtoList.add(chatDto);
        }
    }

    private void chatToDto(List<ChatDto> chatDtoList, List<Chat> chatList) {
        for (Chat chat : chatList) {
            ChatDto chatDto = new ChatDto();
            chatDto.setChatId(chat.getChatId());
            chatDto.setIsApply(chat.getIsApply());
            chatDto.setUserAdmin(chatDto.getUserAdmin());
            chatDto.setUserCustomer(chat.getUserCustomer());
            chatDto.setChatDetailSet(new HashSet<>(this.chatDetailService.findTop1ByChatId(chat.getChatId())));
            chatDtoList.add(chatDto);
        }
    }
}
