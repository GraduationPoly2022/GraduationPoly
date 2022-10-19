package com.shop.repository;

import com.shop.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {
    Optional<Accessory> findByAccessoryProduct_prodId(Long prodId);
}
