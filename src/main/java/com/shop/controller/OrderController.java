package com.shop.controller;


import com.shop.dto.OrderDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.enumEntity.OrderStatus;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.IOrderDetailService;
import com.shop.services.IOrderService;
import com.shop.services.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IUserService userService;

//    @GetMapping("/all")
//    public List<OrderDto> getAll() {
//
//        return ;
//    }

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> insertOrder(@RequestBody OrderDto orderDto) {
        ResponseEntity<ResponseMessage> message = null;
        Order checkUserAndStatus = this.orderService.findByUserAndStatus(orderDto.getUser(), OrderStatus.CART);
        try {
            if (checkUserAndStatus == null) {
                Order order = new Order();
                BeanUtils.copyProperties(orderDto, order);
                Order saveOrder = this.orderService.createOrder(order);
                OrderDetail orderDetails = new OrderDetail();
                BeanUtils.copyProperties(orderDto.getOrderDetail(), orderDetails, "orders_orderDetail");
                orderDetails.setOrders_orderDetail(saveOrder);
                OrderDetail saveOrderDetail = this.orderDetailService.createOrderDetail(orderDetails);
                if (saveOrder != null && saveOrderDetail != null) {
                    message = ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(StatusMessage.OK, "Create Order OK", saveOrder));
                }
            } else {
                OrderDetail checkOrderDetail = this.orderDetailService
                        .findByOrderAndProductAndUserAndStatus(
                                checkUserAndStatus,
                                orderDto.getOrderDetail().getProducts_orderDetail(),
                                orderDto.getUser(),
                                OrderStatus.CART
                        );
                OrderDetail orderDetails;
                if (checkOrderDetail == null) {
                    orderDetails = new OrderDetail();
                    BeanUtils.copyProperties(orderDto.getOrderDetail(), orderDetails, "orders_orderDetail", "qty");
                    orderDetails.setOrders_orderDetail(checkUserAndStatus);
                    if (orderDto.getOrderDetail().getQty() == 0) {
                        orderDetails.setQty(1);
                    } else {
                        orderDetails.setQty(orderDto.getOrderDetail().getQty());
                    }
                    OrderDetail saveOrderDetail = this.orderDetailService.createOrderDetail(orderDetails);
                    if (saveOrderDetail != null) {
                        message = ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseMessage(StatusMessage.OK, "OK", saveOrderDetail));
                    }
                } else {
                    checkOrderDetail.setQty(checkOrderDetail.getQty() + orderDto.getOrderDetail().getQty());
                    OrderDetail saveOrderDetail = this.orderDetailService.createOrderDetail(checkOrderDetail);
                    if (saveOrderDetail != null) {
                        message = ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseMessage(StatusMessage.OK, "OK", saveOrderDetail));
                    }
                }
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }


}
