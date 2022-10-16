package com.shop.controller;

import com.shop.dto.OrderDetailDto;
import com.shop.dto.OrderDto;
import com.shop.dto.ProductDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.entity.Products;
import com.shop.enumEntity.OrderStatus;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.IOrderDetailService;
import com.shop.services.IOrderService;
import com.shop.services.IProductService;
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

    @Autowired
    private IProductService productService;


    //GetAll Order and Order Detail
    @GetMapping("/{email}/{status}")
    public ResponseEntity<ResponseMessage> getAll(
            @PathVariable("email") String email,
            @PathVariable("status") OrderStatus status) {
        ResponseEntity<ResponseMessage> message = null;
        List<Order> list = this.orderService.findAll(email, status);
        List<OrderDto> orderDtoList = transfer(list, email, status);
        if (!list.isEmpty()) {
            message = ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(StatusMessage.OK, "Get all data successfully", orderDtoList));
        } else {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.NOT_FOUND, "Not found data", null));
        }
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
                BeanUtils.copyProperties(orderDto, order, "status");
                order.setStatus(orderDto.getStatus());
                Order saveOrder = this.orderService.createOrder(order);
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOdde(saveOrder);
                Products p = new Products();
                BeanUtils.copyProperties(orderDto.getProduct(), p);
                orderDetail.setProdOdde(p);
                orderDetail.setQty(orderDto.getQty());
                orderDetail.setPrice(orderDto.getProduct().getPriceProd());
                OrderDetail saveOrderDetail = this.orderDetailService.createOrderDetail(orderDetail);
                if (saveOrder != null && saveOrderDetail != null) {
                    message = ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(StatusMessage.OK, "Create Order OK", saveOrder));
                }
            } else {
                Products p = new Products();
                BeanUtils.copyProperties(orderDto.getProduct(), p);
                OrderDetail checkOrderDetail = this.orderDetailService
                        .findByOrderAndProductAndUserAndStatus(
                                checkUserAndStatus,
                                p,
                                orderDto.getUsersOd(),
                                OrderStatus.CART
                        );
                OrderDetail saveOrderDetail;
                if (checkOrderDetail == null) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOdde(checkUserAndStatus);
                    orderDetail.setQty(orderDto.getQty());
                    orderDetail.setProdOdde(p);
                    orderDetail.setPrice(orderDto.getProduct().getPriceProd());
                    saveOrderDetail = this.orderDetailService.createOrderDetail(orderDetail);
                } else {
                    checkOrderDetail.setQty(checkOrderDetail.getQty() + orderDto.getQty());
                    saveOrderDetail = this.orderDetailService.createOrderDetail(checkOrderDetail);
                }
                if (saveOrderDetail != null) {
                    message = ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(StatusMessage.OK, "OK", saveOrderDetail));
                }
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }

    private List<OrderDto> transfer(List<Order> orders, String email, OrderStatus status) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setOdId(order.getOdId());
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setDeliveryDate(order.getDeliveryDate());
            orderDto.setRecipientDate(order.getRecipientDate());
            orderDto.setReceiver(order.getReceiver());
            orderDto.setPhoneReceive(order.getPhoneReceive());
            orderDto.setAddressReceive(order.getAddressReceive());
            orderDto.setStatus(order.getStatus());
            orderDto.setAmount(order.getAmount());
            orderDto.setPaymentReceived(order.getPaymentReceived());
            orderDto.setUsersOd(order.getUsersOd());
            List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
            order.getOrderDetails().forEach(ls -> {
                OrderDetailDto orderDetailDto = new OrderDetailDto();
                orderDetailDto.setOddeId(ls.getOddeId());
                orderDetailDto.setQty(ls.getQty());
                orderDetailDto.setPrice(ls.getProdOdde().getPriceProd());
                ProductDto productDto = new ProductDto();
                BeanUtils.copyProperties(ls.getProdOdde(), productDto);
                orderDetailDto.setProdOdde(productDto);
                orderDetailDtos.add(orderDetailDto);
            });
            orderDto.setLsOrderDetails(orderDetailDtos);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}

