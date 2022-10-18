package com.shop.repository;

import com.shop.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findBySmartPhone_spId(Long spId);

    List<Products> findByAccessoryProd_accessoryId(Long accessoryId);
}
