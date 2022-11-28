package com.shop.repository;

import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUsersOdAndStatus(User user, OrderStatus status);

    List<Order> findByUsersOd_emailAndStatus(String email, OrderStatus status);

    @Transactional
    @Procedure(procedureName = "REVENUE_STATISTICS_BY_YEAR")
    List<Object[]> revenueStatisticsByYear(int year);

    @Transactional
    @Procedure(procedureName = "SHIPPER_STATISTICS_ORDER")
    List<Object[]> statisticsShipperOrder(Long userShipperId, int years);

    Optional<Integer> countByStatus(OrderStatus status);

    List<Order> findByShippers_UserShippers_UserId(Long userId);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByUsersOd_userIdAndStatus(Long usersOd_userId, OrderStatus status);

    Integer countByRecipientDateBetweenAndStatus(Date recipientDate, Date recipientDate2, OrderStatus status);

    Integer countByOrderDateBetweenAndStatus(Date recipientDateStart, Date recipientDateEnd, OrderStatus status);

    List<Order> findDistincByRecipientDateBetweenAndStatus(Date recipientDateStart, Date recipientDateEnd, OrderStatus status);


}
