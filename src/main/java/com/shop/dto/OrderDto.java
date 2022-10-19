package com.shop.dto;

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
    private String receiver;
    private String phoneReceiver;
    private String addressReceiver;
    private OrderStatus status;
    private Double amount;
    private PaymentEnum paymentReceived;
    private DeviceEnum deviceUse;
    private User usersOd;
    private List<OrderDetailDto> lsOrderDetails;
    private ProductDto product;
    private Integer qty = 1;
    private ReturnDto returns;
    private ShipperDto shippers;
}
