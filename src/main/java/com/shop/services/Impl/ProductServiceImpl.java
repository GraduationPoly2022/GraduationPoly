package com.shop.services.Impl;

import com.shop.dto.ProductDto;
import com.shop.entity.ImageDetail;
import com.shop.entity.Products;
import com.shop.repository.ProductRepository;
import com.shop.services.IImageDetailService;
import com.shop.services.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private IImageDetailService imageDetailService;

    @Override
    public Products createProducts(Products products) {
        return this.productRepository.save(products);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productsList = this.productRepository.findAll();
        for (Products products : productsList) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(products, productDto, "imageDetails", "productsEnum");
            List<ImageDetail> imageDetails = this.imageDetailService.findByProd(products);
            productDto.setImageDetails(imageDetails);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    
}
