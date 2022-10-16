package com.shop.controller;

import com.shop.dto.ProductDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.*;
import com.shop.enumEntity.ProductsEnum;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;
    @Autowired
    private ILapTopService iLapTopService;
    @Autowired
    private IAccessoryService iAccessoryService;
    @Autowired
    private ISmartPhoneService iSmartPhoneService;
    @Autowired
    private IImageDetailService imageDetailService;

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createProduct(@RequestBody ProductDto productDto) {
        ResponseEntity<ResponseMessage> message;
        if (!productDto.getProductsEnum().equals(ProductsEnum.ACCESSORY)
                && !productDto.getProductsEnum().equals(ProductsEnum.LAPTOP)
                && !productDto.getProductsEnum().equals(ProductsEnum.SMARTPHONE)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(StatusMessage.NOT_FOUND,
                    "Product is invalid", null));
        }
        try {
            Products products = new Products();
            BeanUtils.copyProperties(productDto, products, "prodId", "accessoryProd",
                    "smartPhone", "laptop", "imageDetails", "productsEnum");
            Products productsSave = this.iProductService.createProducts(products);
            // crate product
            ProductDto productDtoReturn = new ProductDto();
            BeanUtils.copyProperties(productsSave, productDtoReturn, "accessoryProd",
                    "smartPhone", "laptop", "imageDetails", "productsEnum");
            productDtoReturn.setAvailable(productDto.getAvailable());
            List<ImageDetail> imageDetails = new ArrayList<>();
            productDto.getImageDetails().forEach(imageDetail -> {
                Products product = new Products();
                product.setProdId(productsSave.getProdId());
                imageDetail.setProdImde(product);
                imageDetails.add(imageDetail);
            });
            if (!imageDetails.isEmpty()) {
                List<ImageDetail> imageDetailSave = this.imageDetailService.creImageDetail(imageDetails);
                productDtoReturn.setImageDetails(imageDetailSave);
            }

            switch (productDto.getProductsEnum().toString()) {
                case "LAPTOP" -> {
                    Laptop laptop = productDto.getLaptop();
                    laptop.setLaptopProd(productsSave);
                    Laptop laptopSave = this.iLapTopService.createLaptop(laptop);
                    productDtoReturn.setLaptop(laptopSave);
                }
                case "SMARTPHONE" -> {
                    SmartPhone smartPhone = productDto.getSmartPhone();
                    smartPhone.setSpProd(productsSave);
                    SmartPhone smartPhoneSave = this.iSmartPhoneService.createSmartPhone(smartPhone);
                    productDtoReturn.setSmartPhone(smartPhoneSave);
                }
                case "ACCESSORY" -> {
                    Accessory accessory = productDto.getAccessoryProd();
                    accessory.setAccessoryProduct(productsSave);
                    Accessory accessorySave = this.iAccessoryService.createAccessory(accessory);
                    productDtoReturn.setAccessoryProd(accessorySave);
                }
            }
            message = ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(StatusMessage.OK,
                    "Create Product is successful!", productDtoReturn));
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(StatusMessage.ERROR,
                    e.getMessage(), null));
        }
        return message;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMessage> findAllProduct() {
        List<ProductDto> productDtoList = this.iProductService.findAllProducts();
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", productDtoList));
    }
}

