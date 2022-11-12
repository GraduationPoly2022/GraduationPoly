package com.shop.controller;

import com.shop.dto.OrderDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.*;
import com.shop.enumEntity.OrderStatus;
import com.shop.enumEntity.PaymentEnum;
import com.shop.enumEntity.Reason;
import com.shop.enumEntity.StatusMessage;
import com.shop.repository.ShipperRepository;
import com.shop.services.IOrderDetailService;
import com.shop.services.IOrderService;
import com.shop.services.IReturnService;
import com.shop.utils.Convert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private ShipperRepository shipperRepository;

    @Autowired
    private IReturnService returnService;


    //GetAll Order and Order Detail
    @GetMapping("/{email}/{status}")
    public ResponseEntity<ResponseMessage> getAll(
            @PathVariable("email") String email,
            @PathVariable("status") OrderStatus status) {
        List<Order> list = this.orderService.findAll(email, status);
        return this.transferList(list);
    }

    //GetAll Order and Order Detail Admin
    @GetMapping("/admin")
    public ResponseEntity<ResponseMessage> getAllAdmin() {
        List<Order> list = this.orderService.findAllAdmin();
        return this.transferList(list);
    }

    //Insert Order and Order Detail
    @PostMapping("/")
    public ResponseEntity<ResponseMessage> insertOrder(@RequestBody OrderDto orderDto) {
        ResponseEntity<ResponseMessage> message = null;
        Order checkUserAndStatus = this.orderService.findByUserAndStatus(orderDto.getUsersOd(), OrderStatus.CART);
        try {
            if (checkUserAndStatus == null) {
                Order order = new Order();
                BeanUtils.copyProperties(orderDto, order, "status", "orderDate");
                order.setStatus(OrderStatus.CART);
                order.setOrderDate(new Date());
                Order saveOrder = this.orderService.saveOrUpdate(order);
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOdde(saveOrder);
                Products products = new Products();
                BeanUtils.copyProperties(orderDto.getProduct(), products);
                orderDetail.setProdOdde(products);
                orderDetail.setQty(orderDto.getQty());
                orderDetail.setPrice(orderDto.getProduct().getPriceProd());
                OrderDetail saveOrderDetail = this.orderDetailService.saveOrUpdate(orderDetail);
                if (saveOrder != null && saveOrderDetail != null) {
                    message = ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(StatusMessage.OK, "Create Order OK", saveOrder));
                }
            } else {
                Products products = new Products();
                BeanUtils.copyProperties(orderDto.getProduct(), products);
                OrderDetail checkOrderDetail = this.orderDetailService
                        .findByOrderAndProductAndUserAndStatus(
                                checkUserAndStatus,
                                products,
                                orderDto.getUsersOd(),
                                OrderStatus.CART
                        );
                OrderDetail saveOrderDetail;
                if (checkOrderDetail == null) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOdde(checkUserAndStatus);
                    orderDetail.setQty(orderDto.getQty());
                    orderDetail.setProdOdde(products);
                    orderDetail.setPrice(orderDto.getProduct().getPriceProd());
                    saveOrderDetail = this.orderDetailService.saveOrUpdate(orderDetail);
                } else {
                    checkOrderDetail.setQty(checkOrderDetail.getQty() + orderDto.getQty());
                    saveOrderDetail = this.orderDetailService.saveOrUpdate(checkOrderDetail);
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

    //Insert Shipper
    @PostMapping("/shipper")
    public ResponseEntity<ResponseMessage> addShipper(@RequestBody OrderDto orderDto) {
        ResponseEntity<ResponseMessage> message = null;
        Order order = this.orderService.checkOrders(orderDto.getOdId());
        try {
            if (order != null && order.getStatus().equals(OrderStatus.WAITING_FOR_PRODUCT)) {
                Shipper shipper = new Shipper();
                BeanUtils.copyProperties(orderDto.getShippers(), shipper,
                        "total", "shipperId", "orderShipper");
                if (order.getPaymentReceived() == PaymentEnum.PAID) {
                    shipper.setTotal(0d);
                } else {
                    shipper.setTotal(order.getAmount());
                }
                shipper.setOrderShipper(order);
                Shipper save = this.shipperRepository.save(shipper);
                message = ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(StatusMessage.OK, "Get all data successfully", save));
            }
        } catch (Exception e) {
            Order orderCallBack = this.orderService.checkOrders(orderDto.getOdId());
            orderCallBack.setStatus(OrderStatus.WAITING_FOR_CONFIRM);
            orderService.saveOrUpdate(order);
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }

    //Insert Return
    @PostMapping("/return")
    public ResponseEntity<ResponseMessage> addReturn(@RequestBody OrderDto orderDto) {
        ResponseEntity<ResponseMessage> message = null;
        Order order = this.orderService.checkOrders(orderDto.getOdId());
        if (order != null && order.getStatus().equals(OrderStatus.DELIVERING)) {
            Return returns = new Return();
            BeanUtils.copyProperties(orderDto.getReturns(), returns,
                    "returnDate", "reason", "orderReturn", "shippersReturn");
            returns.setReturnDate(new Date());
            returns.setOrderReturn(order);
            Shipper shipper = this.shipperRepository.findByOrderShipper_odId(orderDto.getOdId()).orElse(null);
            returns.setShippersReturn(shipper);
            switch (orderDto.getReturns().getReason()) {
                case NO_REASON:
                    returns.setReason(Reason.NO_REASON);
                    break;
                case ARRIVED_TOO_LATE:
                    returns.setReason(Reason.ARRIVED_TOO_LATE);
                    break;
                case ITEM_WAS_DEFECTIVE:
                    returns.setReason(Reason.ITEM_WAS_DEFECTIVE);
                    break;
                case NOT_QUALITY:
                    returns.setReason(Reason.NOT_QUALITY);
                    break;
                case DESCRIPTION_INCORRECTLY:
                    returns.setReason(Reason.DESCRIPTION_INCORRECTLY);
                    break;
                case POOR_SERVICE_COVERAGE:
                    returns.setReason(Reason.POOR_SERVICE_COVERAGE);
                    break;
                case MISSING_ACCESSORIES:
                    returns.setReason(Reason.MISSING_ACCESSORIES);
                    break;
                case DIFFERENT_ORDERS:
                    returns.setReason(Reason.DIFFERENT_ORDERS);
                    break;
                case DAMAGED_IN_TRANSIT:
                    returns.setReason(Reason.DAMAGED_IN_TRANSIT);
                    break;
                default:
                    returns.setReason(Reason.ANOTHER_REASON);
                    break;
            }
            Return saveReturns = this.returnService.saveReturn(returns);
            if (saveReturns != null) {
                message = ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(StatusMessage.OK, "Save successfully", saveReturns));
            }
        } else {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.NOT_FOUND, "Not found order", null));
        }
        return message;
    }


    @GetMapping("/amount-order")
    public Double getAmountOrder(@RequestParam("orderID") Long orderID,
                                 @RequestParam("userID") Long userID) {
        return this.orderDetailService.totalPrice(orderID, userID);
    }

    @GetMapping("/find-all-shipper")
    public ResponseEntity<ResponseMessage> findAllShipperse() {
        List<User> list = this.orderService.findAllShipperse();
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get all", list));
    }

    //Update Status
    @PutMapping("/")
    public ResponseEntity<ResponseMessage> updateStatusOrder(
            @RequestBody OrderDto orderDto) {
        ResponseEntity<ResponseMessage> message = null;
        Order order = this.orderService.checkOrders(orderDto.getOdId());
        try {
            if (order != null) {
                switch (order.getStatus()) {
                    case CART:
                        order.setReceiver(Convert.CapitalAll(orderDto.getReceiver()));
                        order.setAddressReceiver(orderDto.getAddressReceiver());
                        order.setPhoneReceiver(orderDto.getPhoneReceiver());
                        order.setAmount(orderDto.getAmount());
                        if (order.getNotes() != null) order.setNotes(Convert.CapitalOfFirst(orderDto.getNotes()));
                        order.setStatus(OrderStatus.WAITING_FOR_CONFIRM);
                        order.setDeviceUse(orderDto.getDeviceUse());
                        order.setPaymentReceived(orderDto.getPaymentReceived());
                        break;
                    case WAITING_FOR_CONFIRM:
                        if (orderDto.getStatus() != null && orderDto.getStatus().equals(OrderStatus.CANCEL_ORDER)) {
                            order.setStatus(OrderStatus.CANCEL_ORDER);
                        } else {
                            order.setStatus(OrderStatus.WAITING_FOR_PRODUCT);
                        }
                        break;
                    case WAITING_FOR_PRODUCT:
                        order.setDeliveryDate(new Date());
                        order.setStatus(OrderStatus.DELIVERING);
                        break;
                    case DELIVERING:
                        if (orderDto.getStatus().equals(OrderStatus.RETURNS_OF_PRODUCTS)) {
                            order.setStatus(OrderStatus.RETURNS_OF_PRODUCTS);
                        } else {
                            order.setRecipientDate(new Date());
                            order.setStatus(OrderStatus.DELIVERED);
                        }
                        break;
                    default:
                        break;
                }
                Order updateOrder = orderService.saveOrUpdate(order);
                if (updateOrder != null) {
                    message = ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(StatusMessage.OK, "Update successfully", updateOrder));
                }
            } else {
                message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseMessage(StatusMessage.NOT_FOUND, "Not found order", null));
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }


    //General method FindAll
    private ResponseEntity<ResponseMessage> transferList(List<Order> list) {
        List<OrderDto> orderDtoList = this.orderService.transfer(list);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(StatusMessage.OK, "Get all data successfully", orderDtoList));
    }

    @GetMapping("/count-ta/{userId}")
    public Integer abc(@PathVariable("userId") Long userId) {
        return this.orderService.countOrderConfirmation();
    }
}