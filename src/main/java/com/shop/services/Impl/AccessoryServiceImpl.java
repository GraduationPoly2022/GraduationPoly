package com.shop.services.Impl;

import com.shop.entity.Accessory;
import com.shop.repository.AccessoryRepository;
import com.shop.services.IAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessoryServiceImpl implements IAccessoryService {
    @Autowired
    private AccessoryRepository accessoryRepository;

    @Override
    public Accessory createAccessory(Accessory accessory) {
        return this.accessoryRepository.save(accessory);
    }


}
