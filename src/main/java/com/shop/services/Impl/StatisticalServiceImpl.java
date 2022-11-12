package com.shop.services.Impl;

import com.shop.repository.OrderDetailRepository;
import com.shop.services.IStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatisticalServiceImpl implements IStatisticalService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Transactional
    @Override
    public List<Object[]> thongKeTop10(Integer month, Integer year) {
        return this.orderDetailRepository.ThongKe_Top10_Product(month, year);
    }

    @Transactional
    @Override
    public List<Object[]> thongKeTop10ByYear(Integer year) {
        return this.orderDetailRepository.ThongKe_Top10_ProductByYear(year);
    }

}
