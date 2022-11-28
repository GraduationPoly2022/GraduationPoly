package com.shop.controller;

import com.shop.dto.FavoriteDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.Favorites;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.IFavoriteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {
    @Autowired
    private IFavoriteService iFavoriteService;

    @PostMapping("/")
    //Add favorites to favorites
    public ResponseEntity<ResponseMessage> createFavorite(@RequestBody FavoriteDto favoriteDto) {
        ResponseEntity<ResponseMessage> message = null;
        Favorites checked = this.iFavoriteService.findByUserAndProd(favoriteDto.getUserFavorite().getUserId(),
                favoriteDto.getFavProd().getProdId());
        try {
            if (checked == null) {
                Favorites favorites = new Favorites();
                BeanUtils.copyProperties(favoriteDto, favorites, "favId", "countFavorite");
                Favorites favoriteSaved = this.iFavoriteService.createFavorite(favorites);
                message = ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseMessage(StatusMessage.OK, "Added to favorites!", favoriteSaved)
                );
            } else if (checked.getYourFavorite()) {
                Optional<Favorites> delete = this.iFavoriteService.deleteFavorites(favoriteDto.getUserFavorite().getUserId(),
                        favoriteDto.getFavProd().getProdId());
                message = ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseMessage(StatusMessage.OK, "deleted is successfully!", delete)
                );
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }


    @GetMapping("/find-count-fav/{userId}")
    //Find total number of user favorites by user id => not used so realtime
    public ResponseEntity<ResponseMessage> findCountFav(@PathVariable("userId") Long userId) {
        Integer countFav = this.iFavoriteService.countFavorites(userId);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get data", countFav));
    }

    @GetMapping("/")
    //Find all favorites
    public ResponseEntity<ResponseMessage> findAll() {
        List<Favorites> favorites = this.iFavoriteService.findAll();
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get data", favorites));
    }

    @GetMapping("/find-by-user/{userId}")
    //Find favorites by user id
    public ResponseEntity<ResponseMessage> findByUser(@PathVariable("userId") Long userId) {
        List<Favorites> favorites = this.iFavoriteService.findByUser(userId);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get data", favorites));

    }
}
