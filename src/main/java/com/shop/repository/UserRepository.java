package com.shop.repository;


import com.shop.entity.User;
import com.shop.enumEntity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRoleSet_RoleName(@NonNull RoleName roleName);


}
