package com.shop.services.Impl;


import com.shop.entity.ImageDetail;
import com.shop.repository.ImageDetailRepository;
import com.shop.services.IImageDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImageDetailImpl implements IImageDetailService {
    @Autowired
    private ImageDetailRepository imageDetailRepository;

    @Override
    public List<ImageDetail> creImageDetail(List<ImageDetail> imageDetail) {
        return this.imageDetailRepository.saveAll(imageDetail);
    }

    @Override
    public List<ImageDetail> findByProduct(Long prodId) {
        return this.imageDetailRepository.findByProdImde_prodId(prodId);
    }
}
