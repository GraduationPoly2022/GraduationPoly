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
    private String warranty;
    private Double priceProd;
    private String notes;
    private Laptop laptop;
    private SmartPhone smartPhone;
    private ProductionCompany prodPco;
    private Accessory accessoryProd;
    private Category catProd;
    private List<ImageDetail> imageDetails;
    private ProductsEnum productsEnum;
    private Integer rating;
}
