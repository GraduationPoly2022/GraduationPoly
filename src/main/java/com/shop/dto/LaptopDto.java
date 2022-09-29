package com.shop.dto;


import com.shop.entity.ImageDetail;
import com.shop.entity.Producer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaptopDto {
    private Long laptopId;
    private String monitor;
    private String cpu;
    private String ram;
    private String disk;
    private String gpu;
    private String systemOperator;
    private String gateway;
    private String design;
    private String special;
    private String sizeAndWeight;
    private Date yearOfManufacture;
    private String notes;
    private Producer laptopProducer;
    private List<ImageDetail> imageDetailList;

}
