package com.shop.services;

import com.shop.entity.Favorites;

import java.util.List;
import java.util.Optional;

public interface IFavoriteService {
    Favorites createFavorite(Favorites favorites);


    Favorites findByUserAndProd(Long userId, Long prodId);

    List<Favorites> findAll();

    List<Favorites> findByUser(Long userId);

    Integer countFavorites(Long userId);

    Optional<Favorites> deleteFavorites(Long userId, Long prodId);

    Favorites findYourFavorite(Long prodId);
}
