package com.shop.services;

import com.shop.dto.ProductDto;
import com.shop.entity.Products;

import java.util.List;

public interface IProductService {
    Products createProducts(Products products);

    List<ProductDto> findAllProducts();

    Products findById(Long prodId);
}
