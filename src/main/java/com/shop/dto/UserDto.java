package com.shop.dto;

import com.shop.enumEntity.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String fullName;
    private String imageUrl;
    private RoleName authority;
}
