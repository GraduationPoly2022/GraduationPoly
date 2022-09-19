package com.shop.controller;

import com.shop.dto.ResponseMessage;
import com.shop.dto.UserDto;
import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.enumEntity.AuthenticationProvider;
import com.shop.enumEntity.RoleName;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.IRoleService;
import com.shop.services.IUserService;
import com.shop.utils.Convert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

import static com.shop.helper.CheckMail.emailExists;
import static com.shop.utils.ImageDefault.IMAGE_DEFAULT_URL;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void CreateUser(@RequestBody UserDto userDto, User user, IRoleService roleService, String password) {
        userDto.setAddress(Convert.CapitalAllFirstLetter(userDto.getAddress()));
        userDto.setFullName(Convert.CapitalAllFirstLetter(userDto.getFullName()));
        Role role = new Role();
        if (userDto.getAuthority() == null) {
            role.setRoleName(RoleName.CLIENT);
        } else {
            role.setRoleName(userDto.getAuthority());
        }
        BeanUtils.copyProperties(userDto, user);
        if (userDto.getImageUrl() == null) {
            user.setImageUrl(IMAGE_DEFAULT_URL);
        } else {
            user.setImageUrl(userDto.getImageUrl());
        }
        user.setUserId(userDto.getUserId());
        user.setPassword(password);
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByRoleName(role.getRoleName()));
        user.setAuthProvider(AuthenticationProvider.LOCAL_PROVIDER);
        user.setRoleSet(roles);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createUser(@RequestParam("code") String code, @RequestBody UserDto userDto) {
        ResponseEntity<ResponseMessage> message = null;
        User user = new User();
        if (this.userService.findByEmail(userDto.getEmail()) != null) {
            UserDto userError = new UserDto();
            userError.setEmail("Email already exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "Email already exists", userError));
        }
        if (!emailExists(userDto.getEmail())) {
            UserDto userError = new UserDto();
            userError.setEmail("Email address dones not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "Email address dones not exist", userError));
        }
        try {
            CreateUser(userDto, user, this.roleService, this.passwordEncoder.encode(userDto.getPassword()));
            User u = this.userService.createUser(user);
            if (u != null) message = ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(StatusMessage.OK, "Succesful user creation", u));

        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }

    @PutMapping("/")
    public ResponseEntity<ResponseMessage> handleUpdateUser(@RequestBody UserDto userDto) {
        ResponseEntity<ResponseMessage> message = null;
        User user = new User();
        try {
            CreateUser(userDto, user, this.roleService, this.passwordEncoder.encode(userDto.getPassword()));
            User u = this.userService.createUser(user);
            if (u != null) message = ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(StatusMessage.OK, "User update successful", u));

        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMessage> findAllUsers() {
        return ResponseEntity
                .ok(new ResponseMessage(
                        StatusMessage.OK, "Get data from db successfully", this.userService.findAlUsers())
                );
    }
}