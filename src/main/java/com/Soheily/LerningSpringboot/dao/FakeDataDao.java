package com.Soheily.LerningSpringboot.dao;

import com.Soheily.LerningSpringboot.model.User;

import java.util.*;

public class FakeDataDao implements UserDao {

    private static Map<UUID, User> DataBase;

    static {
        DataBase = new HashMap<>();
        UUID useruid = UUID.randomUUID();
        DataBase.put(useruid, new User(useruid, "Aref",
                "Soheily", User.Gender.Male, 31, "aref.saas@gmail.com"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(DataBase.values());
    }

    @Override
    public User getUser(UUID UserUid) {
        return DataBase.get(UserUid);
    }

    @Override
    public int updateUser(User user) {
        DataBase.put(user.getUserId(), user);
        return 1;
    }

    @Override
    public int removeUser(UUID userId) {
        DataBase.remove(userId);

        return 1;
    }

    @Override
    public int InsertUser(User user) {
        DataBase.put(user.getUserId(), user);
        return 1;
    }
}
