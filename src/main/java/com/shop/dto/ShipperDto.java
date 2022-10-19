package com.shop.dto;

import com.shop.entity.Order;
import com.shop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipperDto {
    private Long shipperId;
    private Double total;
    private String notes;
    private User userShippers;
    private Order orderShipper;
}
