package com.shop.services;

import com.shop.entity.Role;
import com.shop.enumEntity.RoleName;

public interface IRoleService {

    Role createRole(Role role);

    Role findByRoleName(RoleName roleName);
}
