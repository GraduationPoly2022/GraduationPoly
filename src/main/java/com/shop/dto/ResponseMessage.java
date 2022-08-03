package com.shop.dto;

import com.shop.enumEntity.StatusMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
    private StatusMessage status;
    private String message;
    private Object data;
}
