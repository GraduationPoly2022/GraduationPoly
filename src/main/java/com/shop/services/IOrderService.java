package com.shop.services;

import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;

public interface IOrderService {
    Order createOrder(Order order);

    Order findByUserAndStatus(User user, OrderStatus status);
}
