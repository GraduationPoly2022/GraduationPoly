package com.shop.services.Impl;

import com.shop.entity.Laptop;
import com.shop.repository.LapTopRepository;
import com.shop.services.ILapTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LapTopServiceImpl implements ILapTopService {
    @Autowired
    private LapTopRepository lapTopRepository;

    @Override
    public Laptop createLaptop(Laptop laptop) {
        return this.lapTopRepository.save(laptop);
    }

}
