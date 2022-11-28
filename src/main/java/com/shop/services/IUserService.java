package com.shop.services;

import com.shop.dto.UserDto;
import com.shop.entity.User;

import java.util.List;

public interface IUserService {
    User findByEmail(String email);

    User createUser(User user);

    List<UserDto> findAlUsers();

    User findById(Long id);

    Integer totalUserOfClient();
}
