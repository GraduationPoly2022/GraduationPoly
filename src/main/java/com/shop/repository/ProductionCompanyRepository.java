package com.shop.repository;

import com.shop.entity.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionCompanyRepository extends JpaRepository<ProductionCompany, Long> {
    List<ProductionCompany> findDistinctAllByPcoPod_catProd_catId(Long pcoPod_catProd_catId);
}
