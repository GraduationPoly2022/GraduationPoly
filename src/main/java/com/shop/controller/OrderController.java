package com.shop.controller;

import com.shop.dto.OrderDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.enumEntity.OrderStatus;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.IOrderDetailService;
import com.shop.services.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;


    //GetAll Order and Order Detail
    @GetMapping("/get-data/{userId}")
    public ResponseEntity<ResponseMessage> getAll(@PathVariable("userId") Long userId) {
        ResponseEntity<ResponseMessage> message = null;
        List<Order> list = this.orderService.findAll(OrderStatus.CART, userId);
        List<OrderDto> orderDtoList = transfer(list);
        message = ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(StatusMessage.OK, "Get all data successfully", orderDtoList));
        return message;
    }

    //Insert Order and Order Detail
    @PostMapping("/")
    public ResponseEntity<ResponseMessage> insertOrder(@RequestBody OrderDto orderDto) {
        ResponseEntity<ResponseMessage> message = null;
        Order checkUserAndStatus = this.orderService.findByUserAndStatus(orderDto.getUsersOd(), OrderStatus.CART);
        try {
            if (checkUserAndStatus == null) {
                Order order = new Order();
                BeanUtils.copyProperties(orderDto, order);
                Order saveOrder = this.orderService.createOrder(order);
                OrderDetail orderDetails = new OrderDetail();
                BeanUtils.copyProperties(orderDto.getOrderDetails(), orderDetails, "Odde");
                orderDetails.setOdde(saveOrder);
                OrderDetail saveOrderDetail = this.orderDetailService.createOrderDetail(orderDetails);
                if (saveOrder != null && saveOrderDetail != null) {
                    message = ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(StatusMessage.OK, "Create Order OK", saveOrder));
                }
            } else {
                OrderDetail checkOrderDetail = this.orderDetailService
                        .findByOrderAndProductAndUserAndStatus(
                                checkUserAndStatus,
                                orderDto.getOrderDetails().getProdOdde(),
                                orderDto.getUsersOd(),
                                OrderStatus.CART
                        );
                OrderDetail orderDetails;
                if (checkOrderDetail == null) {
                    orderDetails = new OrderDetail();
                    BeanUtils.copyProperties(orderDto.getOrderDetails(), orderDetails, "Odde", "qty");
                    orderDetails.setOdde(checkUserAndStatus);
                    if (orderDto.getOrderDetails().getQty() == 0) {
                        orderDetails.setQty(1);
                    } else {
                        orderDetails.setQty(orderDto.getOrderDetails().getQty());
                    }
                    OrderDetail saveOrderDetail = this.orderDetailService.createOrderDetail(orderDetails);
                    if (saveOrderDetail != null) {
                        message = ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseMessage(StatusMessage.OK, "OK", saveOrderDetail));
                    }
                } else {
                    checkOrderDetail.setQty(checkOrderDetail.getQty() + orderDto.getOrderDetails().getQty());
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

    private List<OrderDto> transfer(List<Order> orders) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setOdId(order.getOdId());
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setDeliveryDate(order.getDeliveryDate());
            orderDto.setRecipientDate(order.getRecipientDate());
            orderDto.setReceive(order.getReceive());
            orderDto.setPhoneReceive(order.getPhoneReceive());
            orderDto.setAddressReceive(order.getAddressReceive());
            orderDto.setStatus(order.getStatus());
            orderDto.setAmount(order.getAmount());
            orderDto.setPaymentReceived(order.getPaymentReceived());
            orderDto.setUsersOd(order.getUsersOd());
            List<OrderDetail> lsOrderDetails = this.orderDetailService.findAll(order.getOdId(), order.getUsersOd(), OrderStatus.CART);
            orderDto.setLsOrderDetails(lsOrderDetails);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}