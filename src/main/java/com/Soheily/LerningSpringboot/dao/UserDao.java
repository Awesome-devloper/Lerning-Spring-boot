package com.Soheily.LerningSpringboot.dao;

import com.Soheily.LerningSpringboot.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    List<User> getAllUsers();
    User getUser(UUID UserUid);

    int updateUser(User user);

    int removeUser(UUID userId);

    int InsertUser(User user);
}
