package com.shop.services.Impl;

import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;
import com.shop.repository.OrderRepository;
import com.shop.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order saveOrUpdate(Order order) {
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

    @Override
    public Order checkOrders(Long id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order delete(Long id) {
        Order order = this.orderRepository.findById(id).orElse(null);
        this.orderRepository.deleteById(id);
        return order;
    }

    @Override
    public List<Order> findAllAdmin() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order checkOrderAndUserAndStatus(Long odId, String email, OrderStatus status) {
        return this.orderRepository.findByOdIdAndUsersOd_emailAndStatus(odId, email, status).orElse(null);
    }

}
