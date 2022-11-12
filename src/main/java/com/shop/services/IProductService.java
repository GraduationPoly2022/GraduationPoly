package com.shop.services;

import com.shop.dto.ProductDto;
import com.shop.entity.Products;

import java.util.List;

public interface IProductService {
    Products createProducts(Products products);

    List<ProductDto> findAllProducts();


    ProductDto findAcSpLtByProduct(Long prodId);

    List<ProductDto> findByCategory(Long catId);

    List<ProductDto> findByProdPco(Long pcoId);

    List<ProductDto> findByProdName(String value);

    Products findByProducts(Long prodId);

    List<ProductDto> findTop4Products(Long catId);
}
