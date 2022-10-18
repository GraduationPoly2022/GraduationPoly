package com.shop.services;


import com.shop.entity.ImageDetail;
import com.shop.entity.Products;

import java.util.List;

public interface IImageDetailService {
    List<ImageDetail> creImageDetail(List<ImageDetail> imageDetail);

    List<ImageDetail> findByProd(Products products);

    List<ImageDetail> findByProductId(Long prodId);
}
