package com.shop.dto;


import com.shop.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaptopDto {
    private Long laptopId;
    private Products laptopProd;
    private String monitor;
    private String cpu;
    private String ram;
    private String disk;
    private String gpu;
    private String sysop;
    private String gateway;
    private String design;
    private String special;
    private String sizeAndWeight;
    private Date yom;
    private String notes;

}
