package com.shop.dto;

import com.shop.entity.Category;
import com.shop.enumEntity.ProductsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDto {
    private Long prodId;
    private String prodName;
    private String imageUrlMain;
    private Boolean available;
    private Integer warranty;
    private List<AccessoryDto> accessoryDtoList;
    private List<SmartPhoneDto> smartPhoneDtoList;
    private List<LaptopDto> laptopDtoList;
    private Category categoriesProduct;
    private ProductsEnum productsEnum;
}
