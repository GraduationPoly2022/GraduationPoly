package com.shop.repository;


import com.shop.entity.ImageDetail;
import com.shop.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageDetailRepository extends JpaRepository<ImageDetail, Long> {
    List<ImageDetail> findByProdImde(Products products);

    List<ImageDetail> findByProdImde_prodId(Long prodId);

    void deleteByImageName(String imageName);

    Optional<ImageDetail> findByImageNameEndingWith(String imageName);
}
