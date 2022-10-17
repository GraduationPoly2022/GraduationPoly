package com.shop.repository;

import com.shop.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {

    Accessory findByAccessoryId(Long accessoryId);

}
