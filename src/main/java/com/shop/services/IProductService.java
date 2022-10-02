package com.shop.services;

import com.shop.dto.ProductListDto;
import com.shop.entity.Products;

import java.util.List;

public interface IProductService {
    Products createProducts(Products products);

    List<ProductListDto> findAll();
}
