package com.shop.services;

import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;

import java.util.List;

public interface IOrderService {
    Order saveOrUpdate(Order order);

    Order findByUserAndStatus(User user, OrderStatus status);

    List<Order> findAll(String email, OrderStatus status);

    Order checkOrders(Long id);

    Order delete(Long id);

    List<Order> findAllAdmin();
}
