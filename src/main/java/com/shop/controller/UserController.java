package com.shop.controller;

<<<<<<< HEAD
import com.shop.dto.ResponseMessage;
import com.shop.dto.UserDto;
import com.shop.entity.Role;
import com.shop.enumEntity.RoleName;
import com.shop.entity.User;
=======
>>>>>>> 42aaa18 (v1)
import com.shop.services.Impl.RoleServiceImpl;
import com.shop.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 42aaa18 (v1)

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

<<<<<<< HEAD
    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createUser(@RequestBody UserDto userDto) {
        ResponseEntity<ResponseMessage> message = null;
        if (this.userService.findByEmail(userDto.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("Error", "Email is already exists", null));
        }
        try {
            userDto.setAddress(Convert.CapitalAllFirstLetter(userDto.getAddress()));
            userDto.setFullName(Convert.CapitalAllFirstLetter(userDto.getFullName()));
            Role role = new Role();
            if (userDto.getRole() == null) {
                role.setRoleName(RoleName.CLIENT);
            } else {
                switch (userDto.getRole()) {
                    case "admin" -> role.setRoleName(RoleName.ADMIN);
                    case "shipper" -> role.setRoleName(RoleName.SHIPPER);
                    default -> role.setRoleName(RoleName.CLIENT);
                }
            }
            Set<Role> roles = new HashSet<>();
            roles.add(this.roleService.findByRoleName(role.getRoleName()));
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            user.setRoleSet(roles);
            User u = this.userService.createUser(user);
            if (u != null) message = ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("Ok", "Create user is successfully", u));
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("Error", e.getMessage(), null));
        }
        return message;
    }
}
=======

}
>>>>>>> 42aaa18 (v1)
