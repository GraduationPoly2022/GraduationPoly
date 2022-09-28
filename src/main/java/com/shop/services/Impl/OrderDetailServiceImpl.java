package com.shop.services.Impl;

import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.entity.Products;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;
import com.shop.repository.OrderDetailRepository;
import com.shop.services.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return this.orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail findByOrderAndProductAndUserAndStatus(Order order, Products products, User user, OrderStatus status) {
        return this.orderDetailRepository
                .findByOrdersDetailAndProductsOrderDetailAndOrdersDetail_UsersOrdersAndOrdersDetail_status(order, products, user, status).orElse(null);
    }

}
