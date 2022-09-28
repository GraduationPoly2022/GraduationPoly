package com.shop.services.Impl;

import com.shop.entity.SmartPhone;
import com.shop.repository.SmartPhoneRepository;
import com.shop.services.ISmartPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartPhoneImpl implements ISmartPhoneService {
    @Autowired
    private SmartPhoneRepository smartPhoneRepository;

    @Override
    public SmartPhone createSmartPhone(SmartPhone smartPhone) {
        return this.smartPhoneRepository.save(smartPhone);
    }
}
