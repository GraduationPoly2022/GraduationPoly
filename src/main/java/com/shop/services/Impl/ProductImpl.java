package com.shop.services.Impl;

import com.shop.entity.Products;
import com.shop.repository.ProductRepository;
import com.shop.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Products createProducts(Products products) {
        return this.productRepository.save(products);
    }
}
