package com.shop.services.Impl;

import com.shop.entity.Role;
import com.shop.entity.RoleName;
import com.shop.repository.RoleRepository;
import com.shop.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRole(Role role){
        return this.roleRepository.save(role);
    }

    @Override
    public Role findByRoleName(RoleName roleName){
        return this.roleRepository.findByRoleName(roleName).orElse(null);
    }
}
