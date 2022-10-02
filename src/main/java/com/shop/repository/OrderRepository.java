package com.shop.repository;

import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUsersOdAndStatus(User user, OrderStatus status);

    List<Order> findByStatusAndUsersOd_UserId(OrderStatus status, Long userId);
}
