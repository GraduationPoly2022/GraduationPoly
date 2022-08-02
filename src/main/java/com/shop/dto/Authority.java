package com.shop.dto;

import com.shop.enumEntity.RoleName;
import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private final RoleName authority;

    public Authority(RoleName authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority.toString();
    }
}
