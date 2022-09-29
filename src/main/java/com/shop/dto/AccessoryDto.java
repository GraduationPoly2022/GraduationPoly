package com.shop.dto;

import com.shop.entity.ImageDetail;
import com.shop.entity.Producer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Producer accessoriesProducer;
    private List<ImageDetail> imageDetailList;
}
