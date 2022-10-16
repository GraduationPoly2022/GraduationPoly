package com.shop.services.Impl;

import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;
import com.shop.repository.OrderDetailRepository;
import com.shop.repository.OrderRepository;
import com.shop.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public Order createOrder(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Order findByUserAndStatus(User user, OrderStatus status) {
        return this.orderRepository.findByUsersOdAndStatus(user, status).orElse(null);
    }

    @Override
    public List<Order> findAll(String email, OrderStatus status) {
        return this.orderRepository.findByUsersOd_emailAndStatus(email, status);
    }

}
