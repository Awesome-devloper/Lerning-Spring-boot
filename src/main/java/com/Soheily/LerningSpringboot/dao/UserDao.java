package com.Soheily.LerningSpringboot.dao;

import com.Soheily.LerningSpringboot.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    List<User> selectAllUsers();
   Optional<User> selectUserByUid(UUID UserUid);

    int updateUser(User user);

    int deleteUser(UUID userId);

    int insertUser(UUID userUid,User user);
}
