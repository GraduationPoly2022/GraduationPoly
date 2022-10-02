package com.shop.dto;

import com.shop.entity.OrderDetail;
import com.shop.entity.Return;
import com.shop.entity.User;
import com.shop.enumEntity.DeviceEnum;
import com.shop.enumEntity.OrderStatus;
import com.shop.enumEntity.PaymentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long odId;
    private Date orderDate;
    private Date deliveryDate;
    private Date recipientDate;
    private String receive;
    private String phoneReceive;
    private String addressReceive;
    private OrderStatus status;
    private Double amount;
    private PaymentEnum paymentReceived;
    private DeviceEnum deviceUse;
    private User usersOd;
    private OrderDetail orderDetails;
    private List<OrderDetail> lsOrderDetails;
    private Return returns;

}