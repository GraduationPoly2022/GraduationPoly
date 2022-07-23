package com.shop.services;

import com.shop.entity.Role;
import com.shop.entity.RoleName;

public interface IRoleService {
    Role createRole(Role role);

    Role findByRoleName(RoleName roleName);
}
