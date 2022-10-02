package com.shop.services.Impl;

import com.shop.dto.AccessoryDto;
import com.shop.dto.LaptopDto;
import com.shop.dto.ProductListDto;
import com.shop.dto.SmartPhoneDto;
import com.shop.entity.ImageDetail;
import com.shop.entity.Products;
import com.shop.repository.ProductRepository;
import com.shop.services.*;
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
    @Autowired
    private ILapTopService iLapTopService;
    @Autowired
    private IAccessoryService iAccessoryService;
    @Autowired
    private ISmartPhoneService smartPhoneService;

    @Override
    public Products createProducts(Products products) {
        return this.productRepository.save(products);
    }

    @Override
    public List<ProductListDto> findAll() {
        List<ProductListDto> productListDto = new ArrayList<>();
        List<Products> product = this.productRepository.findAll();
        for (Products pro : product) {
            List<SmartPhoneDto> smartPhoneDtoList = new ArrayList<>();
            List<LaptopDto> laptopDtoList = new ArrayList<>();
            List<AccessoryDto> accessoryDtoList = new ArrayList<>();
            ProductListDto productListDto1 = new ProductListDto();
            productListDto1.setProdId(pro.getProdId());
            productListDto1.setProdName(pro.getProdName());
            productListDto1.setImageUrlMain(pro.getImageUrlMain());
            productListDto1.setAvailable(pro.isAvailable());
            productListDto1.setWarranty(pro.getWarranty());
//            List<SmartPhone> smartPhoneList = this.smartPhoneService.findSmartPhoneByProduct(pro.getProdId());
//            for (SmartPhone smartPhone : smartPhoneList) {
//                SmartPhoneDto smartPhoneDto = new SmartPhoneDto();
//                smartPhoneDto.setSpId(smartPhone.getSpId());
//                smartPhoneDto.setDisk(smartPhone.getDisk());
//                smartPhoneDto.setCpu(smartPhone.getCpu());
//                smartPhoneDto.setGpu(smartPhone.getGpu());
//                smartPhoneDto.setMonitor(smartPhone.getMonitor());
//                smartPhoneDto.setRam(smartPhone.getRam());
//                smartPhoneDto.setConnector(smartPhone.getConnector());
//                smartPhoneDto.setAfterCamera(smartPhone.getAfterCamera());
//                smartPhoneDto.setBeforeCamera(smartPhone.getBeforeCamera());
//                smartPhoneDto.setBatAChg(smartPhone.getBatAChg());
//                smartPhoneDto.setVideo(smartPhone.getVideo());
//                smartPhoneDto.setUtils(smartPhone.getUtils());
//                smartPhoneDto.setNotes(smartPhone.getNotes());
//                smartPhoneDto.setSysop(smartPhone.getSysop());
////                List<ImageDetail> imageDetails = this.imageDetailService.findByProduct(pro.getProductId());
////                for (ImageDetail imageDetail : imageDetails) {
////                    ImageDetail imageDetailEntity = new ImageDetail();
////                    imageDetailEntity.setProductImages(imageDetail.getProductImages());
////                    imageDetailEntity.setImageDetailId(imageDetail.getImageDetailId());
////                    imageDetailEntity.setImageName(imageDetail.getImageName());
////                    imageDetailSmartPhoneList.add(imageDetailEntity);
////                }
////                smartPhoneDto.setImageDetailList(this.convertEntityToList(pro.getProdId()));
//                smartPhoneDtoList.add(smartPhoneDto);
//            }
//            List<Laptop> laptopList = this.iLapTopService.findLaptopByProduct(pro.getProdId());
//            for (Laptop laptop : laptopList) {
//                LaptopDto laptopDto = new LaptopDto();
//                laptopDto.setLaptopId(laptop.getLaptopId());
//                laptopDto.setGateway(laptop.getGateway());
//                laptopDto.setDesign(laptop.getDesign());
//                laptopDto.setDisk(laptop.getDisk());
//                laptopDto.setCpu(laptop.getCpu());
//                laptopDto.setGpu(laptop.getGpu());
//                laptopDto.setRam(laptop.getRam());
//                laptopDto.setMonitor(laptop.getMonitor());
//                laptopDto.setSpecial(laptop.getSpecial());
//                laptopDto.setSizeAndWeight(laptop.getSizeAndWeight());
//                laptopDto.setSysop(laptop.getSysop());
//                laptopDto.setYom(laptop.getYom());
//                laptopDto.setNotes(laptop.getNotes());
////                List<ImageDetail> imageDetails = this.imageDetailService.findByProduct(pro.getProductId());
////                for (ImageDetail imageDetail : imageDetails) {
////                    ImageDetail imageDetailEntity = new ImageDetail();
////                    imageDetailEntity.setProductImages(imageDetail.getProductImages());
////                    imageDetailEntity.setImageDetailId(imageDetail.getImageDetailId());
////                    imageDetailEntity.setImageName(imageDetail.getImageName());
////                    imageDetailsLapTopList.add(imageDetailEntity);
////                }
////                laptopDto.setImageDetailList(this.convertEntityToList(pro.getProdId()));
//                laptopDtoList.add(laptopDto);
//            }
//            List<Accessory> accessoryList = this.iAccessoryService.findAccessoryByProduct(pro.getProdId());
//            for (Accessory accessory : accessoryList) {
//                AccessoryDto accessoryDto = new AccessoryDto();
//                accessoryDto.setAccessoryId(accessory.getAccessoryId());
//                accessoryDto.setCharging(accessory.getCharging());
//                accessoryDto.setConnector(accessory.getConnector());
//                accessoryDto.setLength(accessory.getLength());
//                accessoryDto.setSpecial(accessory.getSpecial());
//                accessoryDto.setSwitches(accessory.getSwitches());
//                accessoryDto.setSizeKey(accessory.getSizeKey());
//                accessoryDto.setNotes(accessory.getNotes());
////                List<ImageDetail> imageDetails = this.imageDetailService.findByProduct(pro.getProductId());
////                for (ImageDetail imageDetail : imageDetails) {
////                    ImageDetail imageDetailEntity = new ImageDetail();
////                    imageDetailEntity.setProductImages(imageDetail.getProductImages());
////                    imageDetailEntity.setImageDetailId(imageDetail.getImageDetailId());
////                    imageDetailEntity.setImageName(imageDetail.getImageName());
////                    imageDetailAccessoryList.add(imageDetailEntity);
////                }
////                accessoryDto.setImageDetailList(this.convertEntityToList(pro.getProdId()));
//                accessoryDtoList.add(accessoryDto);
//            }
//            productListDto1.setAccessoryDtoList(accessoryDtoList);
//            productListDto1.setSmartPhoneDtoList(smartPhoneDtoList);
//            productListDto1.setLaptopDtoList(laptopDtoList);
//            productListDto.add(productListDto1);
        }
        return productListDto;
    }

    private List<ImageDetail> convertEntityToList(Long id) {
        List<ImageDetail> imageDetails = this.imageDetailService.findByProduct(id);
        List<ImageDetail> tList = new ArrayList<>();
        for (ImageDetail imageDetail : imageDetails) {
            ImageDetail imageDetailEntity = new ImageDetail();
            imageDetailEntity.setProdImde(imageDetail.getProdImde());
            imageDetailEntity.setImdeId(imageDetail.getImdeId());
            imageDetailEntity.setImageName(imageDetail.getImageName());
            tList.add(imageDetailEntity);
        }
        return tList;
    }
}
