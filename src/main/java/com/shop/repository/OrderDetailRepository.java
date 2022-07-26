package com.shop.repository;

import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.entity.Products;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<OrderDetail> findByOddeAndProdOddeAndOdde_usersOdAndOdde_status(Order order, Products products, User user, OrderStatus status);

    Optional<OrderDetail> findByProdOdde_prodIdAndOdde_odIdAndOdde_status(Long prodId, Long id, OrderStatus status);

    List<OrderDetail> findByOdde_odIdAndOdde_usersOd_userIdAndOdde_status(Long odId, Long userId, OrderStatus status);

    List<OrderDetail> findByOdde_odIdAndOdde_status(Long odId, OrderStatus status);

    Optional<Integer> countByOdde_statusAndOdde_usersOd_userId(OrderStatus odde_status, Long odde_usersOd_userId);
    @Transactional
    @Procedure(procedureName = "ThongKe_Top10_Product")
    List<Object[]> ThongKe_Top10_Product(Integer month, Integer year);

    @Transactional
    @Procedure(procedureName = "ThongKe_Top10_ProductByYear")
    List<Object[]> ThongKe_Top10_ProductByYear(Integer year);
}
