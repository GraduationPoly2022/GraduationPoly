package com.shop.controller;

import com.shop.dto.ResponseMessage;
import com.shop.dto.UserDto;
import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.enumEntity.AuthenticationProvider;
import com.shop.enumEntity.RoleName;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.Impl.RoleServiceImpl;
import com.shop.services.Impl.UserServiceImpl;
import com.shop.utils.Convert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createUser(@RequestBody UserDto userDto) {
        ResponseEntity<ResponseMessage> message = null;
        User user = new User();
        if (this.userService.findByEmail(userDto.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "Email is already exists", null));
        }
        try {
            userDto.setAddress(Convert.CapitalAllFirstLetter(userDto.getAddress()));
            userDto.setFullName(Convert.CapitalAllFirstLetter(userDto.getFullName()));
            Role role = new Role();
            if (userDto.getAuthority() == null) {
                role.setRoleName(RoleName.CLIENT);
            } else {
                switch (userDto.getAuthority()) {
                    case "admin" -> role.setRoleName(RoleName.ADMIN);
                    case "shipper" -> role.setRoleName(RoleName.SHIPPER);
                    default -> role.setRoleName(RoleName.CLIENT);
                }
            }
            BeanUtils.copyProperties(userDto, user);
            if (userDto.getImageUrl() == null) {
                user.setImageUrl("abc.png");
            } else {
                user.setImageUrl(userDto.getImageUrl());
            }
            Set<Role> roles = new HashSet<>();
            roles.add(this.roleService.findByRoleName(role.getRoleName()));
            user.setAuthProvider(AuthenticationProvider.LOCAL_PROVIDER);
            user.setRoleSet(roles);
            User u = this.userService.createUser(user);
            if (u != null) message = ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(StatusMessage.OK, "Create user is successfully", u));
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }
}
