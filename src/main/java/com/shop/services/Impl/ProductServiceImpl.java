package com.shop.services.Impl;

import com.shop.dto.ProductDto;
import com.shop.entity.Favorites;
import com.shop.entity.ImageDetail;
import com.shop.entity.Products;
import com.shop.repository.ProductRepository;
import com.shop.services.IFavoriteService;
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
    private IFavoriteService iFavoriteService;
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
        getProductFind(productDtoList, productsList);
        return productDtoList;
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
            Favorites yourFav = this.iFavoriteService.findYourFavorite(prodFindById.getProdId());
            if (yourFav != null) {
                productDto.setYourFavorite(yourFav.getYourFavorite());
            }
            if (rating != null) {
                productDto.setRating(rating);
            }
        }
        return productDto;
    }

    @Override
    public List<ProductDto> findByCategory(Long catId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productFindByCatId = this.productRepository.findByCatProd_catId(catId);
        getProductFind(productDtoList, productFindByCatId);
        return productDtoList;
    }

    @Override
    public List<ProductDto> findByProdPco(Long pcoId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productFind = this.productRepository.findByProdPco_pcoId(pcoId);
        getProductFind(productDtoList, productFind);
        return productDtoList;
    }

    @Override
    public List<ProductDto> findByProdName(String prodName) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productsFind = this.productRepository.findByProdNameContaining(prodName);
        if (!productsFind.isEmpty()) {
            getProductFind(productDtoList, productsFind);
        }
        return productDtoList;
    }

    @Override
    public Products findByProducts(Long prodId) {
        return this.productRepository.findById(prodId).orElse(null);
    }

    @Override
    public List<ProductDto> findTop3Products() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productLapTop = this.productRepository.findLapTop();
        List<Products> productSmartPhone = this.productRepository.findSmartPhone();
        List<Products> productAccessory = this.productRepository.findAccessory();
        if (!productLapTop.isEmpty()) {
            getProductFind(productDtoList, productAccessory, productLapTop, productSmartPhone);
        }
        return productDtoList;
    }

    @SafeVarargs
    public final void getProductFind(List<ProductDto> productDtoList, List<Products>... productFind) {
        if (productFind.length > 0) {
            for (List<Products> productsList : productFind) {
                for (Products products : productsList) {
                    ProductDto productDto = new ProductDto();
                    BeanUtils.copyProperties(products, productDto, "imageDetails", "productsEnum");
                    List<ImageDetail> imageDetails = this.imageDetailService.findByProd(products);
                    if (!imageDetails.isEmpty()) {
                        productDto.setImageDetails(imageDetails);
                    }
                    Integer rating = this.iReviewService.HandleRating(products.getProdId());
                    Favorites yourFav = this.iFavoriteService.findYourFavorite(products.getProdId());
                    if (yourFav != null) {
                        productDto.setYourFavorite(yourFav.getYourFavorite());
                    }
                    if (rating != null) {
                        productDto.setRating(rating);
                    }
                    productDtoList.add(productDto);
                }
            }
        }
    }
}
