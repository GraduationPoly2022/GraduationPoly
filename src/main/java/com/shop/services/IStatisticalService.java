package com.shop.services;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IStatisticalService {
    @Transactional
    List<Object[]> thongKeTop10(Integer month, Integer year);

    @Transactional
    List<Object[]> thongKeTop10ByYear(Integer year);
}
