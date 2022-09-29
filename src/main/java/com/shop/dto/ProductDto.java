package com.shop.dto;


import com.shop.entity.Category;
import com.shop.entity.ImageDetail;
import com.shop.enumEntity.ProductsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private String imageMain;
    private Boolean available;
    private Integer warranty;
    private AccessoryDto accessoryDto;
    private SmartPhoneDto smartPhoneDto;
    private LaptopDto laptopDto;
    private Category categoriesProduct;
    private List<ImageDetail> imageDetail;
    private ProductsEnum productsEnum;
}
