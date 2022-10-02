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

import java.util.List;

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
                .findByOddeAndProdOddeAndOdde_usersOdAndOdde_status(order, products, user, status).orElse(null);
    }

    @Override
    public List<OrderDetail> findAll(Long odId, String email, OrderStatus status) {
        return this.orderDetailRepository
                .findByOdde_odIdAndOdde_usersOd_emailAndOdde_status(odId, email, status);
    }

}
