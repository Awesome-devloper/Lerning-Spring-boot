package com.Soheily.LerningSpringboot.dao;

import com.Soheily.LerningSpringboot.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakeDataDao implements UserDao {

    private  Map<UUID, User> DataBase;

    public FakeDataDao() {
        DataBase = new HashMap<>();
        UUID useruid = UUID.randomUUID();
        DataBase.put(useruid, new User(useruid, "Aref",
                "Soheily", User.Gender.Male, 31, "aref.saas@gmail.com"));
    }

    @Override
    public List<User> selectAllUsers() {
        return new ArrayList<>(DataBase.values());
    }

    @Override
    public Optional<User> selectUserByUid(UUID UserUid) {
        return Optional.ofNullable(DataBase.get(UserUid) );
    }

    @Override
    public int updateUser(User user) {
        DataBase.put(user.getUserId(), user);
        return 1;
    }

    @Override
    public int deleteUser(UUID userId) {
        DataBase.remove(userId);

        return 1;
    }

    @Override
    public int insertUser(UUID userUid,User user) {
        DataBase.put( userUid, user);
        return 1;
    }
}
