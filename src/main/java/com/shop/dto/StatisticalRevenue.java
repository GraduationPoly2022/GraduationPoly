package com.shop.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticalRevenue {
    private Integer months;
    private Integer years;
    private Double totalRevenue;
}
