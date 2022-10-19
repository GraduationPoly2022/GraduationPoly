package com.shop.services.Impl;

import com.shop.dto.UserDto;
import com.shop.entity.User;
import com.shop.enumEntity.RoleName;
import com.shop.repository.UserRepository;
import com.shop.services.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<UserDto> findAlUsers() {
        List<User> result = this.userRepository.findAll();
        List<UserDto> resultList = new ArrayList<>();
        for (User user : result) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto, "authority", "password");
            userDto.setEmail(user.getUsername());
            if (!user.getAuthorities().isEmpty()) {
                userDto.setAuthority(RoleName.valueOf(user.getAuthorities().stream().iterator().next().getAuthority()));
            }
            resultList.add(userDto);
        }
        return resultList;
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
