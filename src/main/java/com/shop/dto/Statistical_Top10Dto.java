package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistical_Top10Dto {
    private String prodName;
    private Double countQty;
    private Double countPrice;
    private Date dateReceived;
}
