package com.shop.dto;

import com.shop.entity.Order;
import com.shop.enumEntity.Reason;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnDto {
    private Long returnId;
    private Date returnDate;
    private Reason reason;
    private String notes;
    private Order orderReturn;
    private ShipperDto shippersReturn;
}
