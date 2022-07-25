package com.shop.services;

import com.shop.entity.User;

public interface IUserService {
    User findByEmail(String email);

    User createUser(User user);
}
