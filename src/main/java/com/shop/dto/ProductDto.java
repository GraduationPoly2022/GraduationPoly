package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long prodId;
    private String prodName;
    private String imageUrlMain;
    private Boolean available;
    private Integer warranty;
}
