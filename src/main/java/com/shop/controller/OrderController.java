package com.shop.controller;

import com.shop.dto.OrderDetailDto;
import com.shop.dto.OrderDto;
import com.shop.dto.ProductDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.*;
import com.shop.enumEntity.OrderStatus;
import com.shop.enumEntity.Reason;
import com.shop.enumEntity.StatusMessage;
import com.shop.repository.ShipperRepository;
import com.shop.services.IOrderDetailService;
import com.shop.services.IOrderService;
import com.shop.services.IReturnService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    //Add Quantity
    @PatchMapping("/")
    public ResponseEntity<ResponseMessage> updateQuantity(@RequestBody OrderDto orderDto) {
        ResponseEntity<ResponseMessage> message = null;
        OrderDetail orderDetail = this.orderDetailService.checkOrders(orderDto.getProduct().getProdId(), orderDto.getOdId(), OrderStatus.CART);
        if (orderDetail != null) {
            if (orderDetail.getQty() == 0) {
                message = this.delete(orderDetail);
            } else {
                orderDetail.setQty(orderDto.getQty());
                OrderDetail updateOdd = this.orderDetailService.saveOrUpdate(orderDetail);
                if (updateOdd != null) {
                    message = ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(StatusMessage.OK, "Update successfully", updateOdd));
                }
            }
        } else {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.NOT_FOUND, "Not found order", null));
        }
        return message;
    }

    //Delete Order
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseMessage> deleteOrder(
            @RequestParam("odId") Long odId,
            @RequestParam("prodId") Long prodId) {
        OrderDetail orderDetails = this.orderDetailService.checkOrders(prodId, odId, OrderStatus.CART);
        return this.delete(orderDetails);
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
                orderDetail.setPrice(orderDto.getProduct().getProdPrice());
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
                    orderDetail.setPrice(orderDto.getProduct().getProdPrice());
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
            if (order != null && order.getStatus().equals(OrderStatus.WAIT_FOR_PRODUCT)) {
                Shipper shipper = new Shipper();
                BeanUtils.copyProperties(orderDto.getShippers(), shipper,
                        "total", "shipperId", "orderShipper");
                shipper.setTotal(order.getAmount());
                shipper.setShipperId(orderDto.getShippers().getShipperId());
                shipper.setOrderShipper(order);
                Shipper save = this.shipperRepository.save(shipper);
                message = ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(StatusMessage.OK, "Get all data successfully", save));
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

    //Update Status
    @PutMapping("/")
    public ResponseEntity<ResponseMessage> updateStatusOrder(
            @RequestBody OrderDto orderDto) {
        ResponseEntity<ResponseMessage> message = null;
        Order order = orderService.checkOrders(orderDto.getOdId());
        try {
            if (order != null) {
                switch (order.getStatus()) {
                    case CART:
                        order.setReceiver(orderDto.getReceiver());
                        order.setAddressReceiver(orderDto.getAddressReceiver());
                        order.setPhoneReceiver(orderDto.getPhoneReceiver());
                        double amount = this.orderDetailService
                                .totalPrice(orderDto.getOdId(), order.getUsersOd().getUserId(),
                                        this.transportFee(orderDto.getProduct().getProdPrice()));
                        order.setAmount(amount);
                        order.setStatus(OrderStatus.WAIT_FOR_CONFIRM);
                        break;
                    case WAIT_FOR_CONFIRM:
                        if (orderDto.getStatus().equals(OrderStatus.CANCEL_ORDER)) {
                            order.setStatus(OrderStatus.CANCEL_ORDER);
                        } else {
                            order.setStatus(OrderStatus.WAIT_FOR_PRODUCT);
                        }
                        break;
                    case WAIT_FOR_PRODUCT:
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

    //Convert
    private List<OrderDto> transfer(List<Order> orders) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setOdId(order.getOdId());
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setDeliveryDate(order.getDeliveryDate());
            orderDto.setRecipientDate(order.getRecipientDate());
            orderDto.setReceiver(order.getReceiver());
            orderDto.setPhoneReceiver(order.getPhoneReceiver());
            orderDto.setAddressReceiver(order.getAddressReceiver());
            orderDto.setStatus(order.getStatus());
            orderDto.setAmount(order.getAmount());
            orderDto.setPaymentReceived(order.getPaymentReceived());
            orderDto.setUsersOd(order.getUsersOd());
            List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
            order.getOrderDetails().forEach(ls -> {
                OrderDetailDto orderDetailDto = new OrderDetailDto();
                orderDetailDto.setOddeId(ls.getOddeId());
                orderDetailDto.setQty(ls.getQty());
                orderDetailDto.setPrice(ls.getProdOdde().getProdPrice());
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

    //General method FindAll
    private ResponseEntity<ResponseMessage> transferList(List<Order> list) {
        ResponseEntity<ResponseMessage> message;
        List<OrderDto> orderDtoList = transfer(list);
        if (!list.isEmpty()) {
            message = ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(StatusMessage.OK, "Get all data successfully", orderDtoList));
        } else {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.NOT_FOUND, "Not found data", null));
        }
        return message;
    }

    //General method Delete
    private ResponseEntity<ResponseMessage> delete(OrderDetail orderDetails) {
        ResponseEntity<ResponseMessage> message = null;
        try {
            if (orderDetails != null) {
                List<OrderDetail> odDetails = this.orderDetailService.checkOrderDetails(orderDetails.getOdde().getOdId(), OrderStatus.CART);
                if (odDetails.size() <= 1) {
                    this.orderDetailService.deleteOrders(orderDetails);
                    this.orderService.delete(orderDetails.getOdde().getOdId());
                } else {
                    this.orderDetailService.deleteOrders(orderDetails);
                }
                message = ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(StatusMessage.OK, "Deleted product id is ", orderDetails.getProdOdde().getProdId()));
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }

    private Double transportFee(double price) {
        double ship;
        if (price > 10000000 && price < 20000000) {
            ship = 300000;
        } else if (price >= 20000000 && price < 30000000) {
            ship = 500000;
        } else if (price >= 30000000 && price < 50000000) {
            ship = 1000000;
        } else if (price >= 50000000) {
            ship = price * 0.05;
        } else {
            ship = 0.0;
        }
        return ship;
    }
}