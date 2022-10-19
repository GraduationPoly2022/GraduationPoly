package com.shop.repository;

import com.shop.entity.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionCompanyRepository extends JpaRepository<ProductionCompany, Long> {
}
