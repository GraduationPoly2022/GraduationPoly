package com.shop.services.Impl;

import com.shop.dto.ProductDto;
import com.shop.entity.ImageDetail;
import com.shop.entity.Products;
import com.shop.repository.ProductRepository;
import com.shop.services.IImageDetailService;
import com.shop.services.IProductService;
import com.shop.services.IReviewService;
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
    private IReviewService iReviewService;
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
        return getProductDtos(productDtoList, productsList);
    }

    @Override
    public ProductDto findAcSpLtByProduct(Long prodId) {
        Products prodFindById = this.productRepository.findById(prodId).orElse(null);
        ProductDto productDto = null;
        if (prodFindById != null) {
            productDto = new ProductDto();
            BeanUtils.copyProperties(prodFindById, productDto, "imageDetails", "productsEnum");
            List<ImageDetail> imageDetails = this.imageDetailService.findByProd(prodFindById);
            if (!imageDetails.isEmpty()) {
                productDto.setImageDetails(imageDetails);
            }
            Integer rating = this.iReviewService.HandleRating(prodFindById.getProdId());
            productDto.setRating(rating);
        }
        return productDto;
    }

    @Override
    public List<ProductDto> findByCategory(Long catId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productFindByCatId = this.productRepository.findByCatProd_catId(catId);
        return getProductDtos(productDtoList, productFindByCatId);
    }

    @Override
    public List<ProductDto> findByProdPco(Long pcoId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productFind = this.productRepository.findByProdPco_pcoId(pcoId);
        return getProductDtos(productDtoList, productFind);
    }

    private List<ProductDto> getProductDtos(List<ProductDto> productDtoList, List<Products> productFind) {
        getProductFind(productDtoList, productFind);
        return productDtoList;
    }

    private void getProductFind(List<ProductDto> productDtoList, List<Products> productFind) {
        for (Products products : productFind) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(products, productDto, "imageDetails", "productsEnum");
            List<ImageDetail> imageDetails = this.imageDetailService.findByProd(products);
            if (!imageDetails.isEmpty()) {
                productDto.setImageDetails(imageDetails);
            }
            Integer rating = this.iReviewService.HandleRating(products.getProdId());
            productDto.setRating(rating);
            productDtoList.add(productDto);
        }
    }

    @Override
    public List<ProductDto> findByProdName(String value) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productsFind = this.productRepository.findByProdNameContaining(value);
        if (productsFind != null) {
            getProductFind(productDtoList, productsFind);

        }
        return productDtoList;
    }

    @Override
    public Products findByProducts(Long prodId) {
        return this.productRepository.findById(prodId).orElse(null);
    }
}
