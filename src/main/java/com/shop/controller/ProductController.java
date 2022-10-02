package com.shop.controller;

import com.shop.dto.*;
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
    public ResponseEntity<ResponseMessage> createProduct(@RequestBody ProductDto productDto
    ) {
        ResponseEntity<ResponseMessage> message = null;
        if (!productDto.getProductsEnum().equals(ProductsEnum.ACCESSORY)
                && !productDto.getProductsEnum().equals(ProductsEnum.LAPTOP)
                && !productDto.getProductsEnum().equals(ProductsEnum.SMARTPHONE)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(StatusMessage.ERROR,
                    "Product is invalid", null));
        }
        try {
            Products products = new Products();
            BeanUtils.copyProperties(productDto, products, "prodId", "accessoryDto",
                    "smartPhoneDto", "laptopDto", "imageDetail", "productsEnum");
            Products productsSave = this.iProductService.createProducts(products);
            // crate product
            ProductDto productDtoReturn = new ProductDto();
            BeanUtils.copyProperties(productsSave, productDtoReturn, "accessoryDto",
                    "smartPhoneDto", "laptopDto", "imageDetail", "productsEnum");
            productDtoReturn.setAvailable(productDto.getAvailable());
            List<ImageDetail> imageDetails = new ArrayList<>();
            productDto.getImageDetail().forEach(imageDetail -> {
                Products product = new Products();
                product.setProdId(productsSave.getProdId());
                imageDetail.setProdImde(product);
                imageDetails.add(imageDetail);
            });
            List<ImageDetail> imageDetailSave = this.imageDetailService.creImageDetail(imageDetails);
            switch (productDto.getProductsEnum().toString()) {
                case "LAPTOP" -> {
                    Laptop laptop = new Laptop();
                    BeanUtils.copyProperties(productDto.getLaptopDto(), laptop, "laptopId", "imageDetailList");
                    Laptop laptopSave = this.iLapTopService.createLaptop(laptop);
                    LaptopDto laptopDto = new LaptopDto();
                    BeanUtils.copyProperties(laptopSave, laptopDto, "imageDetailList");
//                    laptopDto.setImageDetailList(imageDetailSave);
                    productDtoReturn.setLaptopDto(laptopDto);
                }
                case "SMARTPHONE" -> {
                    SmartPhone smartPhone = new SmartPhone();
                    BeanUtils.copyProperties(productDto.getSmartPhoneDto(), smartPhone, "smartPhoneId", "imageDetailList");
                    SmartPhone smartPhoneSave = this.iSmartPhoneService.createSmartPhone(smartPhone);
                    SmartPhoneDto smartPhoneDto = new SmartPhoneDto();
                    BeanUtils.copyProperties(smartPhoneSave, smartPhoneDto, "imageDetailList");
//                    accessoryDto.setImageDetailList(imageDetailSave);
                    productDtoReturn.setSmartPhoneDto(smartPhoneDto);
                }
                case "ACCESSORY" -> {
                    Accessory accessory = new Accessory();
                    BeanUtils.copyProperties(productDto.getAccessoryDto(), accessory, "accessoryId", "imageDetailList");
                    Accessory accessorySave = this.iAccessoryService.createAccessory(accessory);
                    AccessoryDto accessoryDto = new AccessoryDto();
                    BeanUtils.copyProperties(accessorySave, accessoryDto, "imageDetailList");
//                    accessoryDto.setImageDetailList(imageDetailSave);
                    productDtoReturn.setAccessoryDto(accessoryDto);
                }
                default -> message = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(
                        StatusMessage.ERROR, "Key invalid", null));
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
    public ResponseEntity<ResponseMessage> findAll() {
        List<ProductListDto> listDto = this.iProductService.findAll();
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", listDto));
    }
}

