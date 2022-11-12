package com.shop.services;

import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.entity.Products;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;

import java.util.List;

public interface IOrderDetailService {

    OrderDetail saveOrUpdate(OrderDetail orderDetail);

    OrderDetail findByOrderAndProductAndUserAndStatus(Order order, Products products, User user, OrderStatus status);

    OrderDetail checkOrders(Long prodId, Long id, OrderStatus status);

    void deleteOrders(OrderDetail orderDetail);

    Double totalPrice(Long odId, Long userId);

    List<OrderDetail> checkOrderDetails(Long odId, OrderStatus status);

    Integer countProductOrderCart(Long userId);
}
