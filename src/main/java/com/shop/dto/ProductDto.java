package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private String imageMain;
    private String imageUrl;
    private Boolean available;
    private Integer warranty;
}
