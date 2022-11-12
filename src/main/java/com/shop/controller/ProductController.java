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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            // Create product
            ProductDto productDtoReturn = new ProductDto();
            BeanUtils.copyProperties(productsSave, productDtoReturn, "accessoryProd",
                    "smartPhone", "laptop", "imageDetails", "productsEnum");
            productDtoReturn.setAvailable(productDto.getAvailable());
            //Create Image
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
            //Create Accessory,LapTop,SmartPhone
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
            message = ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(StatusMessage.OK, "Create Product is successful!", productDtoReturn));
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(StatusMessage.ERROR,
                    e.getMessage(), null));
        }
        return message;
    }

    @PutMapping("/")
    public ResponseEntity<ResponseMessage> updateProduct(@RequestBody ProductDto productDto) {
        ResponseEntity<ResponseMessage> message;
        if (!productDto.getProductsEnum().equals(ProductsEnum.ACCESSORY)
                && !productDto.getProductsEnum().equals(ProductsEnum.LAPTOP)
                && !productDto.getProductsEnum().equals(ProductsEnum.SMARTPHONE)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(StatusMessage.NOT_FOUND,
                    "Product is invalid", null));
        }
        try {
            Products products = new Products();
            BeanUtils.copyProperties(productDto, products, "accessoryProd",
                    "smartPhone", "laptop", "imageDetails", "productsEnum");
            Products productsSave = this.iProductService.createProducts(products);
            // create product
            ProductDto productDtoReturn = new ProductDto();
            BeanUtils.copyProperties(productsSave, productDtoReturn,
                    "accessoryProd", "smartPhone", "laptop", "imageDetails", "productsEnum");
            productDtoReturn.setAvailable(productDto.getAvailable());
            // create image
            List<ImageDetail> imageDetailFind = this.imageDetailService.findByProductId(productDto.getProdId());
            List<ImageDetail> imageDetailList = new ArrayList<>(productDto.getImageDetails());
            if (!imageDetailFind.isEmpty()) {
                for (ImageDetail detail : imageDetailFind) {
                    for (int i = 0; i < imageDetailList.size(); i++) {
                        if (imageDetailList.get(i).getImageName().equals(detail.getImageName())) {
                            imageDetailList.remove(i);
                            break;
                        }
                    }
                }
            }
            imageDetailList = imageDetailList.stream().peek(item -> {
                Products product = new Products();
                product.setProdId(productDto.getProdId());
                item.setProdImde(product);
            }).collect(Collectors.toList());
            if (!imageDetailList.isEmpty()) {
                List<ImageDetail> imageDetailSave = this.imageDetailService.creImageDetail(imageDetailList);
                productDtoReturn.setImageDetails(imageDetailSave);
            }
            //Create Accessory,LapTop,SmartPhone
            switch (productDto.getProductsEnum().toString()) {
                case "LAPTOP" -> {
                    Laptop laptop = productDto.getLaptop();
                    laptop.setLaptopProd(productsSave);
                    laptop.setLaptopId(productsSave.getProdId());
                    Laptop laptopSave = this.iLapTopService.createLaptop(laptop);
                    productDtoReturn.setLaptop(laptopSave);
                }
                case "SMARTPHONE" -> {
                    SmartPhone smartPhone = productDto.getSmartPhone();
                    smartPhone.setSpProd(productsSave);
                    smartPhone.setSpId(productDto.getProdId());
                    SmartPhone smartPhoneSave = this.iSmartPhoneService.createSmartPhone(smartPhone);
                    productDtoReturn.setSmartPhone(smartPhoneSave);
                }
                case "ACCESSORY" -> {
                    Accessory accessory = productDto.getAccessoryProd();
                    accessory.setAccessoryProduct(productsSave);
                    accessory.setAccessoryId(productDto.getProdId());
                    Accessory accessorySave = this.iAccessoryService.createAccessory(accessory);
                    productDtoReturn.setAccessoryProd(accessorySave);
                }
            }
            message = ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(StatusMessage.OK,
                    "Update Product is successful!", productDtoReturn));
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(StatusMessage.ERROR,
                    e.getMessage(), null));
        }
        return message;
    }

    @PatchMapping("/set-available/{prodId}")
    public ResponseEntity<ResponseMessage> unAvailable(@PathVariable("prodId") Long prodId) {
        ResponseEntity<ResponseMessage> message;
        Products productFindById = this.iProductService.findByProducts(prodId);
        if (productFindById != null) {
            productFindById.setAvailable(!productFindById.isAvailable());
        }
        Products prodSave = this.iProductService.createProducts(productFindById);
        message = ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(StatusMessage.OK,
                "Deleted Product is successful!", prodSave));
        return message;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMessage> findAllProduct() {
        List<ProductDto> productDtoList = this.iProductService.findAllProducts();
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", productDtoList));
    }

    @GetMapping("/product-id/{prodId}")
    public ResponseEntity<ResponseMessage> findProductByProdId(@PathVariable("prodId") Long prodId,
                                                               @RequestParam("lang") String lang) throws IOException {
        ProductDto productDtoList = this.iProductService.findAcSpLtByProduct(prodId, lang);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", productDtoList));
    }

    @GetMapping("/category/{cateId}")
    public ResponseEntity<ResponseMessage> findByCategory(@PathVariable("cateId") Long cateId) {
        ResponseEntity<ResponseMessage> message = null;
        List<ProductDto> productDtoList = this.iProductService.findByCategory(cateId);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", productDtoList));
    }

    @GetMapping("/pco/{pcoId}")
    public ResponseEntity<ResponseMessage> findByPco(@PathVariable("pcoId") Long pcoId) {
        List<ProductDto> productDtoList = this.iProductService.findByProdPco(pcoId);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", productDtoList));
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<ResponseMessage> findByProdName(@RequestParam("prodName") String prodName) {
        List<ProductDto> productDtoList = this.iProductService.findByProdName(prodName);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", productDtoList));
    }
}

