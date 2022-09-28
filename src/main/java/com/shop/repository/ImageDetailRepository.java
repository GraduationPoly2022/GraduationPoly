package com.shop.repository;

import com.shop.entity.imageDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDetailRepository extends JpaRepository<imageDetail, String> {
}
