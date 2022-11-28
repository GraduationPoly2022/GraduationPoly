package com.shop.services.Impl;

import com.shop.dto.OrderDetailDto;
import com.shop.dto.OrderDto;
import com.shop.dto.ProductDto;
import com.shop.dto.ShipperDto;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.entity.Shipper;
import com.shop.entity.User;
import com.shop.enumEntity.OrderStatus;
import com.shop.enumEntity.RoleName;
import com.shop.repository.OrderRepository;
import com.shop.repository.ShipperRepository;
import com.shop.repository.UserRepository;
import com.shop.services.IOrderService;
import com.shop.services.IReviewService;
import com.shop.utils.Convert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IReviewService reviewService;
    @Autowired
    private ShipperRepository shipperRepository;


    @Override
    public Order saveOrUpdate(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Order findByUserAndStatus(User user, OrderStatus status) {
        return this.orderRepository.findByUsersOdAndStatus(user, status).orElse(null);
    }

    @Override
    public List<Order> findAll(String email, OrderStatus status) {
        return this.orderRepository.findByUsersOd_emailAndStatus(email, status);
    }

    @Override
    public List<OrderDto> findAllByPurchased(Long userId) {
        List<Order> orderList = this.orderRepository.findByUsersOd_userIdAndStatus(userId, OrderStatus.DELIVERED);
        return this.transfer(orderList);
    }

    @Override
    public Order checkOrders(Long id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order delete(Long id) {
        Order order = this.orderRepository.findById(id).orElse(null);
        this.orderRepository.deleteById(id);
        return order;
    }

    @Override
    public List<Order> findAllAdmin() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<User> findAllShipperse() {
        return this.userRepository.findByRoleSet_RoleName(RoleName.SHIPPER);
    }

    @Override
    public Integer countOrderConfirmation() {
        return this.orderRepository.countByStatus(OrderStatus.WAITING_FOR_CONFIRM).orElse(0);
    }

    @Override
    public List<OrderDto> findByUserId(Long userId) {
        return this.transfer(this.orderRepository.findByShippers_UserShippers_UserId(userId));
    }

    @Transactional
    @Override
    public List<Object[]> revenueStatisticsByYear(int year) {
        return this.orderRepository.revenueStatisticsByYear(year);
    }

    @Transactional
    @Override
    public List<Object[]> statisticsShipperOrder(Long userShipperId, int years) {
        return this.orderRepository.statisticsShipperOrder(userShipperId, years);
    }

    @Override
    public List<OrderDto> findAllByStatus() {
        return this.transfer(this.orderRepository.findByStatus(OrderStatus.WAITING_FOR_CONFIRM));
    }

    //Convert
    @Override
    public List<OrderDto> transfer(List<Order> orders) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            getTransfer(order, orderDto);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Override
    public List<OrderDto> transfer(List<Order> orders, String email) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            getTransfer(order, orderDto);
            orderDto.setIsReviewed(this.reviewService.isExistByOrderIdAndUserId(order.getOdId(), email));
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    private void getTransfer(Order order, OrderDto orderDto) {
        orderDto.setOdId(order.getOdId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setDeliveryDate(order.getDeliveryDate());
        orderDto.setRecipientDate(order.getRecipientDate());
        orderDto.setReceiver(order.getReceiver());
        orderDto.setPhoneReceiver(order.getPhoneReceiver());
        orderDto.setAddressReceiver(order.getAddressReceiver());
        orderDto.setStatus(order.getStatus());
        orderDto.setAmount(order.getAmount());
        orderDto.setPaymentReceived(order.getPaymentReceived());
        orderDto.setUsersOd(order.getUsersOd());
        Shipper shipper = this.shipperRepository.findByOrderShipper_odId(order.getOdId()).orElse(null);
        ShipperDto shipperDto = new ShipperDto();
        if (shipper != null) {
            shipperDto.setUserShippers(shipper.getUserShippers());
        }
        orderDto.setShippers(shipperDto);
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        order.getOrderDetails().forEach(ls -> {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.setOddeId(ls.getOddeId());
            orderDetailDto.setQty(ls.getQty());
            orderDetailDto.setPrice(ls.getProdOdde().getPriceProd());
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(ls.getProdOdde(), productDto);
            orderDetailDto.setProdOdde(productDto);
            orderDetailDtos.add(orderDetailDto);
        });
        orderDto.setLsOrderDetails(orderDetailDtos);
    }

    @Override
    public Integer totalOrderInMonths(Date start, Date end) {
        return this.orderRepository.countByRecipientDateBetweenAndStatus(start, end, OrderStatus.DELIVERED);
    }

    @Override
    public Integer totalCancelledOrderInMonths() {
        return this.orderRepository.countByOrderDateBetweenAndStatus(Convert.firstDate(), Convert.lastDate(),
                OrderStatus.RETURNS_OF_PRODUCTS);
    }

    @Override
    public Long totalAmountOrderOfMonths(Date start, Date end) {
        List<Order> orderList = this.orderRepository
                .findDistincByRecipientDateBetweenAndStatus(start, end, OrderStatus.DELIVERED);
        return orderList.stream().mapToLong(item -> {
            Set<OrderDetail> orderDetails = item.getOrderDetails();
            return orderDetails.stream()
                    .mapToLong(items -> (long) (items.getQty() * items.getPrice())).reduce(0, Long::sum);
        }).reduce(0, Long::sum);
    }


}
