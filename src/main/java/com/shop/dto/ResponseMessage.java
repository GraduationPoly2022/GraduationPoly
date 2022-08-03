package com.shop.dto;

import com.shop.enumEntity.StatusMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {
    private StatusMessage status;
    private String message;
    private Object data;
}
