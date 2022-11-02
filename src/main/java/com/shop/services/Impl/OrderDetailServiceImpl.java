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
    public OrderDetail saveOrUpdate(OrderDetail orderDetail) {
        return this.orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail findByOrderAndProductAndUserAndStatus(Order order, Products products, User user, OrderStatus status) {
        return this.orderDetailRepository
                .findByOddeAndProdOddeAndOdde_usersOdAndOdde_status(order, products, user, status).orElse(null);
    }

    @Override
    public OrderDetail checkOrders(Long prodId, Long id, OrderStatus status) {
        return this.orderDetailRepository.findByProdOdde_prodIdAndOdde_odIdAndOdde_status(prodId, id, status).orElse(null);
    }

    @Override
    public void deleteOrders(OrderDetail orderDetail) {
        this.orderDetailRepository.delete(orderDetail);
    }


    @Override
    public Double totalPrice(Long odId, Long userId, double transportFee) {
        double total = this.orderDetailRepository
                .findByOdde_odIdAndOdde_usersOd_userIdAndOdde_status(odId, userId, OrderStatus.CART)
                .stream().mapToDouble(item ->
                        item.getQty() * item.getPrice()
                ).sum();
        return total;
    }

    @Override
    public List<OrderDetail> checkOrderDetails(Long odId, OrderStatus status) {
        return this.orderDetailRepository.findByOdde_odIdAndOdde_status(odId, status);
    }

    @Override
    public Integer countProductInCart(Long userId, OrderStatus status) {
        return this.orderDetailRepository.countByOdde_usersOd_userIdAndOdde_status(userId, status);
    }
}