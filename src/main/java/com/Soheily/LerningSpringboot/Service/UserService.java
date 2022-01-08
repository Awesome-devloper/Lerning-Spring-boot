package com.Soheily.LerningSpringboot.Service;

import com.Soheily.LerningSpringboot.dao.UserDao;
import com.Soheily.LerningSpringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers(Optional<String> gender) {
        List<User> users = userDao.selectAllUsers();
        if (!gender.isPresent())
            return users;
        try {
            User.Gender genderValue = User.Gender.valueOf(gender.get());
            return users.stream().filter(x -> x.getGender().equals(genderValue)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalStateException("Invalid gender", e);
        }
    }


    public Optional<User> getUser(UUID UserUid) {
        return userDao.selectUserByUid(UserUid);
    }


    public int updateUser(User user) {
        Optional<User> optionalUser = getUser(user.getUserId());
        if (optionalUser.isPresent()) {
            userDao.updateUser(user);
            return 1;
        }
        return 0;
    }


    public int removeUser(UUID userId) {
        Optional<User> optionalUser = getUser(userId);
        if (optionalUser.isPresent()) {
            userDao.deleteUser(userId);
            return 1;
        }
        return 0;
    }


    public int InsertUser(User user) {
        UUID uuid = UUID.randomUUID();
        user.setUserUid(uuid);
        userDao.insertUser(user.getUserId(), user);
        return 1;
    }
}
