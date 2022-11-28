package com.shop.controllerSocket;

import com.shop.dto.ChatDto;
import com.shop.dto.CommentDto;
import com.shop.entity.Chat;
import com.shop.entity.ChatDetail;
import com.shop.entity.Comment;
import com.shop.entity.CommentDetail;
import com.shop.services.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class controllerSocket {

    @Autowired
    private ICommentService iCommentService;

    @Autowired
    private ICommentDetailService iCommentDetailService;

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IFavoriteService iFavoriteService;

    @Autowired
    private IChatService chatService;

    @Autowired
    private IChatDetailService chatDetailService;

    @MessageMapping("/message/{prodID}/{userId}")
    @SendTo("/smart-choose/message/{prodID}")
    public List<CommentDto> handleMessageComment(CommentDto commentDto,
                                                 @DestinationVariable("prodID") Long prodID,
                                                 @DestinationVariable("userId") Long userId) {
        if (commentDto.getCommentId() == null) {
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentDto, comment, "commentId");
            this.iCommentService.createComment(comment);
        } else {
            CommentDetail commentDetail = new CommentDetail();
            BeanUtils.copyProperties(commentDto.getCommentDt(), commentDetail,
                    "commentDtId");
            this.iCommentDetailService.addCommentDetail(commentDetail);
        }
        List<Comment> list = this.iCommentService.findCommentByProducts(prodID);
        return this.iCommentService.listComment(list, userId);
    }

    @MessageMapping("/message-list/{prodID}/{userId}")
    @SendTo("/smart-choose/message/{prodID}")
    public List<CommentDto> handleMessageComment(@DestinationVariable("prodID") Long prodID,
                                                 @DestinationVariable("userId") Long userId) {
        List<Comment> list = this.iCommentService.findCommentByProducts(prodID);
        return this.iCommentService.listComment(list, userId);
    }

//    @MessageMapping("/notify")
//    @SendTo("/smart-choose/notify")
//    public List<OrderDto> handleNotify() {
//        return this.iOrderService.transfer(this.iOrderService.findAllAdmin());
//    }

    @MessageMapping("/notify-count")
    @SendTo("/smart-choose/notify")
    public Integer getNotifyCount() {
        return this.iOrderService.countOrderConfirmation();
    }

    @MessageMapping("/count-cart/{userid}")
    @SendTo("/smart-choose/count-cart/{userid}")
    public Integer getNotifyCountCart(@DestinationVariable("userid") Long userid) {
        return this.orderDetailService.countProductOrderCart(userid);
    }

    @MessageMapping("/count-favorite/{userid}")
    @SendTo("/smart-choose/count-favorite/{userid}")
    public Integer countFavorite(@DestinationVariable("userid") Long userid) {
        return this.iFavoriteService.countFavorites(userid);
    }

    //strated method chat

    //User request cónultation
    @MessageMapping("/create-chat/{customerId}")
    @SendTo("/smart-choose/create-chat/chat/app/{customerId}")
    public List<ChatDto> createChat(ChatDto chatDto, @DestinationVariable("customerId") Long customerId) {
        Chat chat = new Chat();
        ChatDetail chatDetail = new ChatDetail();
        Chat chatSave;
        Chat chatFind = this.chatService.findByCustomerAndIsEnd(customerId);
        if (chatFind != null) {
            if (chatDto.getUserAdmin() != null) {
                chatFind.setUserAdmin(chatDto.getUserAdmin());
                chatFind.setIsApply(true);
                this.chatService.createChat(chatFind);
                chatDetail.setManChat(true);
                BeanUtils.copyProperties(chatDto.getChatDetail(), chatDetail, "chatDetailId", "manChat", "chat");
                chatDetail.setChat(chatFind);
            } else {
                BeanUtils.copyProperties(chatDto.getChatDetail(), chatDetail, "chatDetailId", "manChat", "chat");
                chatDetail.setManChat(false);
                chatDetail.setChat(chatFind);
            }

        } else {
            BeanUtils.copyProperties(chatDto, chat, "chatId", "chatDetail", "chatDetailSet");
            chat.isEnd(false);
            chatSave = this.chatService.createChat(chat);
            BeanUtils.copyProperties(chatDto.getChatDetail(), chatDetail, "chatDetailId", "manChat", "chat");
            chatDetail.setManChat(false);
            chatDetail.setChat(chatSave);
        }
        this.chatDetailService.createChatDetail(chatDetail);
        return this.chatService.findAllByPublic(customerId);
    }
    
    @SendTo("/private-chat/create-chat/chat/app/{adminId}")
    public List<ChatDto> getHistory(@DestinationVariable("adminId") Long adminId) {
        return this.chatService.findByAdminAndIsApply(adminId);
    }

    @SendTo("/private-chat/create-chat/chat/app/{chatId}/{userID}")
    public ChatDto getHistoryDetails(@DestinationVariable("chatId") Long chatId,
                                     @DestinationVariable("userID") Long userID) {
        return this.chatService.findByIdAndCustomer(chatId, userID);
    }
}
