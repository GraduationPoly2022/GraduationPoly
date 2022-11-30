package com.shop.services.Impl;

import com.shop.entity.ProductionCompany;
import com.shop.repository.ProductionCompanyRepository;
import com.shop.services.IProductionCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCompanyServiceImpl implements IProductionCompanyService {

    @Autowired
    private ProductionCompanyRepository productionCompanyRepository;

    @Override
    public ProductionCompany createProductionCompany(ProductionCompany productionCompany) {
        return this.productionCompanyRepository.save(productionCompany);
    }

    @Override
    public List<ProductionCompany> findAll() {
        return this.productionCompanyRepository.findAll();
    }

    @Override
    public List<ProductionCompany> findAllByCategory(Long categoryId) {
        return this.productionCompanyRepository.findDistinctAllByPcoPod_catProd_catId(categoryId);
    }
}
