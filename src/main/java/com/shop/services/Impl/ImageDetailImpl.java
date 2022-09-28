package com.shop.services.Impl;

import com.shop.entity.imageDetail;
import com.shop.repository.ImageDetailRepository;
import com.shop.services.IImageDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageDetailImpl implements IImageDetailService {
    @Autowired
    private ImageDetailRepository imageDetailRepository;

    @Override
    public imageDetail creImageDetail(imageDetail imageDetail) {
        return this.imageDetailRepository.save(imageDetail);
    }
}
