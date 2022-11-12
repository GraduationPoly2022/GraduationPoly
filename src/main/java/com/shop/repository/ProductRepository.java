package com.shop.repository;

import com.shop.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByCatProd_catId(Long catId);

    List<Products> findByProdPco_pcoId(Long pcoId);

    List<Products> findByProdNameContaining(String values);

    List<Products> findTop4ByCatProd_CatIdOrderByProdId(Long catId);
}
