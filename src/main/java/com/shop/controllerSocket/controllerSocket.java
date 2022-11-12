package com.shop.controllerSocket;

import com.shop.dto.CommentDto;
import com.shop.entity.Comment;
import com.shop.entity.CommentDetail;
import com.shop.services.ICommentDetailService;
import com.shop.services.ICommentService;
import com.shop.services.IOrderDetailService;
import com.shop.services.IOrderService;
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

    @MessageMapping("/message/{prodID}")
    @SendTo("/smart-choose/message/{prodID}")
    public List<CommentDto> handleMessageComment(CommentDto commentDto, @DestinationVariable("prodID") Long prodID) {
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
        return this.iCommentService.listComment(list);
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
}
