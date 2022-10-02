package com.shop.repository;


import com.shop.entity.ImageDetail;
import com.shop.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageDetailRepository extends JpaRepository<ImageDetail, Long> {
    List<ImageDetail> findByProdImde(Products products);
}
