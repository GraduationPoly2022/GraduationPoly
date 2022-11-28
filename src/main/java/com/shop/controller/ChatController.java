package com.shop.controller;

import com.shop.dto.ChatDto;
import com.shop.dto.Message;
import com.shop.dto.OrderDto;
import com.shop.entity.Chat;
import com.shop.entity.ChatDetail;
import com.shop.services.IChatDetailService;
import com.shop.services.IChatService;
import com.shop.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat/app")
public class ChatController {

    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IChatService chatService;
    @Autowired
    private IChatDetailService chatDetailService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        List<Message> messageList = new ArrayList<>();
        List<OrderDto> orderDtoList = this.iOrderService.findAllByStatus();
        List<ChatDto> chatDtoList = this.chatService.findAll();
        for (OrderDto orderDto : orderDtoList) {
            Message message = new Message();
            message.setIsChat(false);
            message.setOrderId(orderDto.getOdId());
            message.setTimeSend(orderDto.getOrderDate());
            messageList.add(message);
        }
        for (ChatDto chatDto : chatDtoList) {
            Message message = new Message();
            message.setIsChat(true);
            message.setChatId(chatDto.getChatId());
            message.setTimeSend(chatDto.getTimeSend());
            messageList.add(message);
        }
        return ResponseEntity.ok().body(messageList);
    }

    @GetMapping("/find-all-admin/{adminID}")
    public ResponseEntity<?> dtoList(@PathVariable Long adminID) {
        List<ChatDto> chatDtoList = this.chatService.findByAdminAndIsApply(adminID);
        return ResponseEntity.ok().body(chatDtoList);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countAdmin() {
        Integer countChat = this.chatService.countByApply();
        return ResponseEntity.ok().body(countChat);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody ChatDto chatDto) {
        Chat chat = this.chatService.findByIdAndIsApply(chatDto.getChatId());
        if (chat != null) {
            chat.setUserAdmin(chatDto.getUserAdmin());
            chat.setIsApply(true);
            this.chatService.createChat(chat);
            return ResponseEntity.ok().body(this.chatService.findByAdminAndIsApply(chat.getUserAdmin().getUserId()));
        }
        return null;
    }

    @GetMapping("/join-apply/{customerId}")
    public ResponseEntity<List<ChatDto>> joinApply(@PathVariable Long customerId) {
        return ResponseEntity.ok().body(this.chatService.findAllByPublic(customerId));
    }

    @GetMapping("/find-by/{chatId}")
    public ResponseEntity<?> findById(@PathVariable("chatId") Long chatId) {
        return ResponseEntity.ok().body(this.chatService.findById(chatId));
    }

    @GetMapping("/find-by/{chatId}/{customerId}")
    public ResponseEntity<?> findById2(@PathVariable("chatId") Long chatId, @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok().body(this.chatService.findByIdAndCustomer(chatId, customerId));
    }

    @GetMapping("/join-apply/{customerId}/{adminId}")
    public ResponseEntity<List<ChatDetail>> joinApply(@PathVariable Long customerId, @PathVariable Long adminId) {
        return ResponseEntity.ok().body(this.chatDetailService.findByChatUserCustomerAndAdmin(customerId, adminId));
    }
}
