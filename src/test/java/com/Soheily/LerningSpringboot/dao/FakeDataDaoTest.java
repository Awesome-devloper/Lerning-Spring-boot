package com.Soheily.LerningSpringboot.dao;

import com.Soheily.LerningSpringboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class FakeDataDaoTest {
private  FakeDataDao fakeDataDao;
    @BeforeEach
    void setUp() {
        fakeDataDao=new FakeDataDao();
    }

    @Test
    void ShouldselectAllUsers() {
        List<User> users= fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(1);
        User user=users.get(0);
        assertThat(user.getAge()).isEqualTo(31);
        assertThat(user.getUserFirstName()).isEqualTo("Aref");
        assertThat(user.getUserLastName()).isEqualTo("Soheily");
        assertThat(user.getGender()).isEqualTo(User.Gender.Male);
        assertThat(user.getEmail()).isEqualTo("aref.saas@gmail.com");
        assertThat(user.getUserId()).isNotNull();
    }

    @Test
    void shouldselectUserByUid() {
        UUID userUidForCur=UUID.randomUUID();
        User user=new User(userUidForCur,"Sahar","Akbarzadeh"
                , User.Gender.FeMale,30,"SaharAkbar@gmail.com");
        fakeDataDao.insertUser(userUidForCur,user);
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);

        Optional<User> curOptional=fakeDataDao.selectUserByUid(userUidForCur);
        assertThat(curOptional.isPresent()).isTrue();
        assertThat(curOptional.get()).isEqualToComparingFieldByField(user);

    }
    @Test
    void shouldNotselectUserByUid() {
    Optional<User> user=fakeDataDao.selectUserByUid(UUID.randomUUID());
    assertThat(user.isPresent()).isFalse();
    }

    @Test
    void ShouldupdateUser() {
       UUID uuid= fakeDataDao.selectAllUsers().get(0).getUserId();
        User user=new User(uuid,"Sahar","Akbarzadeh"
                , User.Gender.FeMale,30,"SaharAkbar@gmail.com");
        fakeDataDao.updateUser(user);
        Optional<User> optionalUser =fakeDataDao.selectUserByUid(uuid);
        assertThat(optionalUser.isPresent()).isTrue();

        assertThat(fakeDataDao.selectAllUsers()).hasSize(1);
        assertThat(optionalUser.get()).isEqualTo(user);
    }

    @Test
    void ShoulddeleteUser() {
        UUID uuid= fakeDataDao.selectAllUsers().get(0).getUserId();
        fakeDataDao.deleteUser(uuid);
        assertThat(fakeDataDao.selectUserByUid(uuid).isPresent()).isFalse();
        assertThat(fakeDataDao.selectAllUsers()).isEmpty();


    }

    @Test
    void ShouldinsertUser() {
        UUID userUidForCur=UUID.randomUUID();
        User user=new User(userUidForCur,"Sahar","Akbarzadeh"
                , User.Gender.FeMale,30,"SaharAkbar@gmail.com");
        fakeDataDao.insertUser(userUidForCur,user);
        List<User> users=fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(2);
        assertThat(fakeDataDao.selectUserByUid(userUidForCur).get()).isEqualTo(user);

    }
}