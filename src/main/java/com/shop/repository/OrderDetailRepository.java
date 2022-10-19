package com.shop.repository;

import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.entity.Products;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<OrderDetail> findByOddeAndProdOddeAndOdde_usersOdAndOdde_status(Order order, Products products, User user, OrderStatus status);

    Optional<OrderDetail> findByProdOdde_prodIdAndOdde_odIdAndOdde_status(Long prodId, Long id, OrderStatus status);

    List<OrderDetail> findByOdde_odIdAndOdde_usersOd_userIdAndOdde_status(Long odId, Long userId, OrderStatus status);

    List<OrderDetail> findByOdde_odIdAndOdde_status(Long odId, OrderStatus status);


}
