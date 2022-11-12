package com.shop.repository;

import com.shop.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, Long> {

    Optional<Favorites> findByUserFavorite_userIdAndFavProd_prodId(Long userId, Long prodId);

    List<Favorites> findByUserFavorite_userId(Long userId);

    Optional<Integer> countByUserFavorite_userIdAndYourFavoriteTrue(Long userId);

    Optional<Favorites> findByFavProd_prodId(Long prodId);

    @Transactional
    Optional<Favorites> deleteByUserFavorite_userIdAndFavProd_prodId(Long userId, Long prodId);
}
