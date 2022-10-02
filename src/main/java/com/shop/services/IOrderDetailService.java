package com.shop.services;

import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.entity.Products;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;

import java.util.List;

public interface IOrderDetailService {

    OrderDetail createOrderDetail(OrderDetail orderDetail);


    OrderDetail findByOrderAndProductAndUserAndStatus(Order order, Products products, User user, OrderStatus status);


    List<OrderDetail> findAll(Long odId, User user, OrderStatus status);
}
