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
import com.shop.utils.GoogleTranslate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
//        products.setProdName(GoogleTranslate.translate("en", products.getProdName()));
        return this.productRepository.save(products);
    }

    @Override
    public List<ProductDto> findAllProducts(Long userId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productsList = this.productRepository.findAll();
        getProductFind(productDtoList, userId, productsList);
        return productDtoList;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productsList = this.productRepository.findAll();
        getProductFind(productDtoList, productsList);
        return productDtoList;
    }

    @Override
    public ProductDto findAcSpLtByProduct(Long prodId, String lang, Long userId) throws IOException {
        Products prodFindById = this.productRepository.findById(prodId).orElse(null);
        ProductDto productDto = null;
        if (prodFindById != null) {
            productDto = new ProductDto();
            BeanUtils.copyProperties(prodFindById, productDto, "imageDetails", "productsEnum", "notes");
            switch (lang) {
                case "en", "zh" -> {
                    String[] split = prodFindById.getNotes().split(",");
                    StringBuilder sb = new StringBuilder();
                    for (String s : split) {
                        sb.append(GoogleTranslate.translate("vi", lang, s)).append(",");
                    }
                    String str = sb.toString().replaceAll("\\\\n,", " ");
                    productDto.setNotes(str);
                    if (prodFindById.getLaptop() != null) {
                        productDto.getLaptop().setGpu(GoogleTranslate.translate("vi", lang, prodFindById.getLaptop().getGpu()));
                        productDto.getLaptop().setDesign(GoogleTranslate.translate("vi", lang, prodFindById.getLaptop().getDesign()));
                        productDto.getLaptop().setSizeAndWeight(GoogleTranslate.translate("vi", lang, prodFindById.getLaptop().getSizeAndWeight()));
                        productDto.getLaptop().setSpecial(GoogleTranslate.translate("vi", lang, prodFindById.getLaptop().getSpecial()));
                        productDto.getLaptop().setGateway(GoogleTranslate.translate("vi", lang, prodFindById.getLaptop().getGateway()));
                    }
                    if (prodFindById.getSmartPhone() != null) {
                        productDto.getSmartPhone().setConnector(GoogleTranslate.translate("vi", lang, prodFindById.getSmartPhone().getConnector()));
                        productDto.getSmartPhone().setUtils(GoogleTranslate.translate("vi", lang, prodFindById.getSmartPhone().getUtils()));
                    }
                    if (prodFindById.getAccessoryProd() != null) {
                        productDto.getAccessoryProd().setSizeKeyboard(GoogleTranslate.translate("vi", lang, prodFindById.getAccessoryProd().getSizeKeyboard()));
                        productDto.getAccessoryProd().setCharging(GoogleTranslate.translate("vi", lang, prodFindById.getAccessoryProd().getCharging()));
                        productDto.getAccessoryProd().setSizeKey(GoogleTranslate.translate("vi", lang, prodFindById.getAccessoryProd().getSizeKey()));
                        productDto.getAccessoryProd().setConnector(GoogleTranslate.translate("vi", lang, prodFindById.getAccessoryProd().getConnector()));
                        productDto.getAccessoryProd().setSwitches(GoogleTranslate.translate("vi", lang, prodFindById.getAccessoryProd().getSwitches()));
                        productDto.getAccessoryProd().setSpecial(GoogleTranslate.translate("vi", lang, prodFindById.getAccessoryProd().getSpecial()));
                        productDto.getAccessoryProd().setTypeKeyboard(GoogleTranslate.translate("vi", lang, prodFindById.getAccessoryProd().getTypeKeyboard()));
                    }
                    productDto.setWarranty(GoogleTranslate.translate("vi", lang, prodFindById.getWarranty()));
                    if (prodFindById.getSmartPhone() != null) {
                        productDto.getSmartPhone().setGpu(GoogleTranslate.translate("vi", lang, prodFindById.getSmartPhone().getGpu()));
                    }
                }
                default -> productDto.setNotes(prodFindById.getNotes());
            }
            productDto.setReviewsList(this.iReviewService.findByProduct(prodId));
            getProductImage(prodFindById, productDto, userId);
        }
        return productDto;
    }

    private void getProductImage(Products prodFindById, ProductDto productDto, Long userId) {
        List<ImageDetail> imageDetails = this.imageDetailService.findByProd(prodFindById);
        if (!imageDetails.isEmpty()) {
            productDto.setImageDetails(imageDetails);
        }
        Integer rating = this.iReviewService.HandleRating(prodFindById.getProdId());
        Favorites yourFav = this.iFavoriteService.findByUserAndProd(userId, prodFindById.getProdId());
        if (yourFav != null) {
            productDto.setYourFavorite(yourFav.getYourFavorite());
        }
        if (rating != null) {
            productDto.setRating(rating);
        }
    }

    private void getProductImage(Products prodFindById, ProductDto productDto) {
        List<ImageDetail> imageDetails = this.imageDetailService.findByProd(prodFindById);
        if (!imageDetails.isEmpty()) {
            productDto.setImageDetails(imageDetails);
        }
        Integer rating = this.iReviewService.HandleRating(prodFindById.getProdId());
        if (rating != null) {
            productDto.setRating(rating);
        }
    }

    @Override
    public List<ProductDto> findByCategory(Long catId, Long userId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productFindByCatId = this.productRepository.findByCatProd_catId(catId);
        getProductFind(productDtoList, userId, productFindByCatId);
        return productDtoList;
    }

    @Override
    public List<ProductDto> findByCategory(Long catId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productFindByCatId = this.productRepository.findByCatProd_catId(catId);
        getProductFind(productDtoList, productFindByCatId);
        return productDtoList;
    }

    @Override
    public List<ProductDto> findByProdPco(Long pcoId, Long userId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productFind = this.productRepository.findByProdPco_pcoId(pcoId);
        getProductFind(productDtoList, userId, productFind);
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
        List<Products> productLapTop = this.productRepository.findTop3ByCatProd_NameOrderByDateAddedDesc("Laptop");
        List<Products> productSmartPhone = this.productRepository.findTop3ByCatProd_NameOrderByDateAddedDesc("Smartphone");
        List<Products> productAccessory = this.productRepository.findTop3ByCatProd_NameOrderByDateAddedDesc("Accessory");
        if (!productLapTop.isEmpty()) {
            getProductFind(productDtoList, productAccessory, productLapTop, productSmartPhone);
        }
        return productDtoList;
    }

    @SafeVarargs
    public final void getProductFind(List<ProductDto> productDtoList, Long userId, List<Products>... productFind) {
        if (productFind.length > 0) {
            for (List<Products> productsList : productFind) {
                for (Products products : productsList) {
                    ProductDto productDto = new ProductDto();
                    BeanUtils.copyProperties(products, productDto, "imageDetails", "productsEnum");
                    productDto.setAvailable(products.isAvailable());
                    getProductImage(products, productDto, userId);
                    productDtoList.add(productDto);
                }
            }
        }
    }

    @SafeVarargs
    public final void getProductFind(List<ProductDto> productDtoList, List<Products>... productFind) {
        if (productFind.length > 0) {
            for (List<Products> productsList : productFind) {
                for (Products products : productsList) {
                    ProductDto productDto = new ProductDto();
                    BeanUtils.copyProperties(products, productDto, "imageDetails", "productsEnum");
                    productDto.setAvailable(products.isAvailable());
                    getProductImage(products, productDto);
                    productDtoList.add(productDto);
                }
            }
        }
    }


    @Override
    public List<ProductDto> findTop4Products(Long catId, Long userId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> productsFind = this.productRepository.findTop4ByCatProd_catIdOrderByProdId(catId);
        if (!productsFind.isEmpty()) {
            getProductFind(productDtoList, userId, productsFind);
        }
        return productDtoList;
    }

    @Override
    public Integer countAllProduct() {
        return Math.toIntExact(this.productRepository.count());
    }
}
