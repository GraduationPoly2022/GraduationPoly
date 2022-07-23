package com.shop.config;

import com.shop.entity.Role;
import com.shop.entity.RoleName;
import com.shop.services.Impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (this.roleService.findByRoleName(RoleName.ADMIN) == null) {
            Role role = new Role();
            role.setRoleName(RoleName.ADMIN);
            this.roleService.createRole(role);
        }
        if (this.roleService.findByRoleName(RoleName.CLIENT) == null) {
            Role role = new Role();
            role.setRoleName(RoleName.CLIENT);
            this.roleService.createRole(role);
        }
        if (this.roleService.findByRoleName(RoleName.SHIPPER) == null) {
            Role role = new Role();
            role.setRoleName(RoleName.SHIPPER);
            this.roleService.createRole(role);
        }
    }
}
