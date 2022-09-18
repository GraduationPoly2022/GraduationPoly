package com.shop.services;

import com.shop.entity.User;

import java.util.List;

public interface IUserService {
    User findByEmail(String email);

    User createUser(User user);

    List<User> findAlUsers();

    User findById(Long id);
}
