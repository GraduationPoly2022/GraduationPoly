package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Favorites {
    @EmbeddedId
    private ProductUser favId = new ProductUser();

    @ManyToOne
    @MapsId("userId")
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User userFavorite;

    @ManyToOne
    @MapsId("prodId")
    @JsonIgnore
    @JoinColumn(name = "prod_id")
    private Products favProd;

    private Boolean yourFavorite;

    public Favorites() {
    }

    public Favorites(User userFavorite, Products favProd, Boolean yourFavorite) {
        this.favId = new ProductUser(userFavorite.getUserId(), favId.getProdId());
        this.userFavorite = userFavorite;
        this.favProd = favProd;
        this.yourFavorite = yourFavorite;
    }

    public ProductUser getFavId() {
        return favId;
    }

    public void setFavId(ProductUser favId) {
        this.favId = favId;
    }

    public User getUserFavorite() {
        return userFavorite;
    }

    public void setUserFavorite(User userFavorite) {
        this.userFavorite = userFavorite;
    }

    public Products getFavProd() {
        return favProd;
    }

    public void setFavProd(Products favProd) {
        this.favProd = favProd;
    }

    public Boolean getYourFavorite() {
        return yourFavorite;
    }

    public void setYourFavorite(Boolean yourFavorite) {
        this.yourFavorite = yourFavorite;
    }
}
