package com.shop.services;

import com.shop.dto.ProductDto;
import com.shop.entity.Products;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    Products createProducts(Products products);

    List<ProductDto> findAllProducts(Long userId);

    List<ProductDto> findAllProducts();

    ProductDto findAcSpLtByProduct(Long prodId, String lang, Long userId) throws IOException;

    List<ProductDto> findByCategory(Long catId, Long userId);

    List<ProductDto> findByCategory(Long catId);

    List<ProductDto> findByProdPco(Long pcoId, Long userId);

    List<ProductDto> findByProdName(String prodName);

    Products findByProducts(Long prodId);

    List<ProductDto> findTop3Products();

    List<ProductDto> findTop4Products(Long catId, Long userId);

    Integer countAllProduct();
}
