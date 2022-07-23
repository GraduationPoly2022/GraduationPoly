package com.shop.services.Impl;

import com.shop.entity.User;
import com.shop.repository.UserRepository;
import com.shop.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }
}
