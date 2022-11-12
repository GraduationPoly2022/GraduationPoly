package com.shop.services.Impl;

import com.shop.entity.Favorites;
import com.shop.repository.FavoriteRepository;
import com.shop.services.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements IFavoriteService {
    @Autowired
    private FavoriteRepository favoritesRepository;

    @Override
    public Favorites createFavorite(Favorites favorites) {
        return this.favoritesRepository.save(favorites);
    }

    @Override
    public Favorites findByUserAndProd(Long userId, Long prodId) {
        return this.favoritesRepository.findByUserFavorite_userIdAndFavProd_prodId(userId, prodId).orElse(null);
    }

    @Override
    public List<Favorites> findAll() {
        return this.getModel();
    }

    @Override
    public List<Favorites> findByUser(Long userId) {
        return this.getModelByUser(userId);
    }

    @Override
    public Integer countFavorites(Long userId) {
        return this.favoritesRepository.countByUserFavorite_userIdAndYourFavoriteTrue(userId).orElse(null);
    }

    @Override
    public Optional<Favorites> deleteFavorites(Long userId, Long prodId) {
        return this.favoritesRepository.deleteByUserFavorite_userIdAndFavProd_prodId(userId, prodId);
    }

    @Override
    public Favorites findYourFavorite(Long prodId) {
        return this.favoritesRepository.findByFavProd_prodId(prodId).orElse(null);
    }

    private List<Favorites> getModel() {
        List<Favorites> favoritesList = new ArrayList<>();
        List<Favorites> favoriteFind = this.favoritesRepository.findAll();
        for (Favorites favorites : favoriteFind) {
            Favorites favorite = new Favorites();
            favorite.setFavId(favorites.getFavId());
            favorite.setFavProd(favorites.getFavProd());
            favorite.setUserFavorite(favorites.getUserFavorite());
            favorite.setYourFavorite(favorites.getYourFavorite());
            favoritesList.add(favorite);
        }
        return favoritesList;
    }

    private List<Favorites> getModelByUser(Long userId) {
        List<Favorites> favoritesList = new ArrayList<>();
        List<Favorites> favoriteFind = this.favoritesRepository.findByUserFavorite_userId(userId);
        for (Favorites favorites : favoriteFind) {
            Favorites favorite = new Favorites();
            favorite.setFavId(favorites.getFavId());
            favorite.setFavProd(favorites.getFavProd());
            favorite.setUserFavorite(favorites.getUserFavorite());
            favorite.setYourFavorite(favorites.getYourFavorite());
            favoritesList.add(favorite);
        }
        return favoritesList;
    }

}
