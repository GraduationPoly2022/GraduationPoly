package com.shop.dto;

import com.shop.entity.Products;
import com.shop.entity.imageDetail;
import com.shop.enumEntity.ProductsEnum;
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
    private AccessoryDto accessoryDto;
    private SmartPhoneDto smartPhoneDto;
    private LaptopDto laptopDto;
    private Products productDto;
    private imageDetail imageDetail;
    private ProductsEnum productsEnum;
}
