package com.shop.repository;

import com.shop.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LapTopRepository extends JpaRepository<Laptop, Long> {

}
