package com.shop.services.Impl;

import com.shop.entity.Return;
import com.shop.repository.ReturnRepository;
import com.shop.services.IReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnServiceImpl implements IReturnService {

    @Autowired
    ReturnRepository returnRepository;

    @Override
    public Return saveReturn(Return returns) {
        return this.returnRepository.save(returns);
    }
}
