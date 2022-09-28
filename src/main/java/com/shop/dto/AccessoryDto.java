package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessoryDto {
    private Long accessoryId;
    private String connector;
    private String length;
    private String switches;
    private Double typeKeyboard;
    private Double sizeKey;
    private String special;
    private Double sizeKeyboard;
    private String charging;
    private String notes;
    

}
