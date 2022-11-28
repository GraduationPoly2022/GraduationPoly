package com.shop.dto;

import com.shop.entity.Products;
import com.shop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {
    private Long favId;
    private User userFavorite;
    private Products favProd;
    private Boolean yourFavorite = true;
    private Integer countFavorite;
}
