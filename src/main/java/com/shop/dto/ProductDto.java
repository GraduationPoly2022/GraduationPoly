package com.shop.dto;

import com.shop.entity.*;
import com.shop.enumEntity.ProductsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long prodId;
    private String prodName;
    private String imageUrlMain;
    private Boolean available;
    private Integer warranty;
    private Double prodPrice;
    private String notes;
    private Category catProd;
    private ProductionCompany prodPco;
    private Laptop laptop;
    private SmartPhone smartPhone;
    private Accessory accessoryProd;
    private List<ImageDetail> imageDetails;
    private Integer rating;
    private ProductsEnum productsEnum;
}
