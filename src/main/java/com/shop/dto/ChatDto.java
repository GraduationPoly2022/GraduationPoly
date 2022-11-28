package com.shop.dto;

import com.shop.entity.ChatDetail;
import com.shop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * A DTO for the {@link com.shop.entity.Chat}, {@link com.shop.entity.ChatDetail} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChatDto implements Serializable {
    private Long chatId;
    private User userCustomer;
    private User userAdmin;
    private Boolean isApply = false;
    private Date timeSend;
    private Boolean isEnd = false;
    private ChatDetail chatDetail;
    private Set<ChatDetail> chatDetailSet;


}