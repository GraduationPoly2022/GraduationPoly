package com.shop.repository;

import com.shop.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByCatProd_catId(Long catId);

    List<Products> findByProdPco_pcoId(Long pcoId);

    List<Products> findByProdNameContaining(String prodName);

    List<Products> findTop4ByCatProd_catIdOrderByProdId(Long catId);

    @Query(value = "select * from products p" +
            "         join laptop l on p.prod_id = l.laptop_id " +
            "         where datediff(CURDATE(), p.date_added ) <= 3 " +
            "         order by p.date_added desc, price_prod desc" +
            "         limit 3", nativeQuery = true)
    List<Products> findLapTop();

    @Query(value = "select * from products p" +
            "         join smart_phone sp on p.prod_id = sp.sp_id" +
            "         where datediff(CURDATE(), p.date_added ) <= 3" +
            "         order by p.date_added desc, price_prod " +
            "         desc limit 3", nativeQuery = true)
    List<Products> findSmartPhone();

    @Query(value = "select * from products p" +
            "         join accessory a on p.prod_id = a.accessory_id" +
            "         where datediff(CURDATE(), p.date_added ) <= 3" +
            "         order by p.date_added desc, price_prod " +
            "         desc limit 3", nativeQuery = true)
    List<Products> findAccessory();

    List<Products> findTop4ByCatProd_CatIdOrderByProdId(Long catId);
}

