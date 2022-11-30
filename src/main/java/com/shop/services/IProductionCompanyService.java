package com.shop.services;

import com.shop.entity.ProductionCompany;

import java.util.List;

public interface IProductionCompanyService {
    ProductionCompany createProductionCompany(ProductionCompany productionCompany);

    List<ProductionCompany> findAll();

    List<ProductionCompany> findAllByCategory(Long categoryId);
}
