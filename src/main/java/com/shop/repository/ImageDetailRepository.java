package com.shop.repository;


import com.shop.entity.ImageDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ImageDetailRepository extends JpaRepository<ImageDetail, String> {
    List<ImageDetail> findByProdImde_prodId(Long prodId);

}
