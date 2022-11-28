package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Comparable<Message> {
    private Long orderId;
    private Long chatId;
    private Date timeSend;
    private Boolean isChat;

    @Override
    public int compareTo(Message o) {
        return this.getTimeSend().compareTo(o.getTimeSend());
    }
}
