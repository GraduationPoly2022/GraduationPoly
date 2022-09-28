package com.shop.controller;

import com.shop.dto.AccessoryDto;
import com.shop.dto.LaptopDto;
import com.shop.dto.ResponseMessage;
import com.shop.dto.SmartPhoneDto;
import com.shop.entity.Accessory;
import com.shop.entity.Products;
import com.shop.entity.imageDetail;
import com.shop.enumEntity.ProductsEnum;
import com.shop.services.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pro")
@CrossOrigin("*")
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
    public ResponseEntity<ResponseMessage> createAccessory(@RequestBody AccessoryDto accessoryDto,
                                                           @RequestBody SmartPhoneDto smartPhoneDto,
                                                           @RequestBody LaptopDto laptopDto,
                                                           @RequestBody Products products,
                                                           @RequestBody imageDetail imageDetail,
                                                           Accessory accessory
    ) {
        ResponseEntity<ResponseMessage> message = null;
        Products products1 = new Products();
        Products products2 = iProductService.createProducts(products1);
        List<imageDetail> imageDetailList = new ArrayList<>();
        for (imageDetail i : imageDetailList) {
            imageDetail imageDetail1 = new imageDetail();
            imageDetail1.setProduct_images(products.getProductId());
            imageDetail1.setImageName(i.getImageName());
            imageDetailService.creImageDetail(imageDetail1);
        }
        if (ProductsEnum.ACCESSORY == ProductsEnum.ACCESSORY) {
            BeanUtils.copyProperties(accessoryDto, accessory);
            accessory.setProduct_accessories(products1);
            iAccessoryService.createAccessory(accessory);
        }
        return null;
    }
}

