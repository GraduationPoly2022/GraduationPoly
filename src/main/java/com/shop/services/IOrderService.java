package com.shop.services;

import com.shop.dto.OrderDto;
import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;

import java.util.Date;
import java.util.List;

public interface IOrderService {
    Order saveOrUpdate(Order order);

    Order findByUserAndStatus(User user, OrderStatus status);

    List<Order> findAll(String email, OrderStatus status);

    List<OrderDto> findAllByPurchased(Long userId);

    Order checkOrders(Long id);

    Order delete(Long id);

    List<Order> findAllAdmin();

    List<Object[]> revenueStatisticsByYear(int year);

    List<Object[]> statisticsShipperOrder(Long userShipperId, int years);

    List<User> findAllShipperse();

    Integer countOrderConfirmation();

    List<OrderDto> findByUserId(Long userId);

    //Convert
    List<OrderDto> transfer(List<Order> orders);

    List<OrderDto> findAllByStatus();

    List<OrderDto> transfer(List<Order> orders, String email);

    Integer totalOrderInMonths(Date start, Date end);

    Integer totalCancelledOrderInMonths();

    Long totalAmountOrderOfMonths(Date start, Date end);
}
