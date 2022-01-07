package com.Soheily.LerningSpringboot.Service;

import com.Soheily.LerningSpringboot.dao.FakeDataDao;
import com.Soheily.LerningSpringboot.model.User;
import com.google.common.collect.ImmutableList;
import org.hibernate.validator.internal.util.stereotypes.Immutable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class UserServiceTest {
    @Mock
    private FakeDataDao fakeDataDao;
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(fakeDataDao);
    }

    @Test
    void getAllUsers() {
        UUID userUidForCur = UUID.randomUUID();
        User user = new User(userUidForCur, "Aref", "Soheily"
                , User.Gender.Male, 31, "aref.saas@gmail.com");

        ImmutableList<User> users = new ImmutableList.Builder<User>()
                .add(user)
                .build();
        given(fakeDataDao.selectAllUsers()).willReturn(users);

        List<User> allUsers = userService.getAllUsers();
        User user1=users.get(0);
        userFiledCheck(user1);
        assertThat(allUsers).hasSize(1);
    }

    @Test
    void ShouldgetUser() {
        UUID userUidForCur = UUID.randomUUID();
        User user = new User(userUidForCur, "Aref", "Soheily"
                , User.Gender.Male, 31, "aref.saas@gmail.com");
        given(fakeDataDao.selectUserByUid(userUidForCur)).willReturn(Optional.of(user));

        Optional<User> user1 = userService.getUser(userUidForCur);
        assertThat(user1.isPresent()).isTrue();
        User user2 = user1.get();
        userFiledCheck(user2);

    }

    private void userFiledCheck(User user2) {
        assertThat(user2.getAge()).isEqualTo(31);
        assertThat(user2.getUserFirstName()).isEqualTo("Aref");
        assertThat(user2.getUserLastName()).isEqualTo("Soheily");
        assertThat(user2.getGender()).isEqualTo(User.Gender.Male);
        assertThat(user2.getEmail()).isEqualTo("aref.saas@gmail.com");
        assertThat(user2.getUserId()).isNotNull();
    }

    @Test
    void updateUser() {
        UUID userUidForCur = UUID.randomUUID();
        User user = new User(userUidForCur, "Aref", "Soheily"
                , User.Gender.Male, 31, "aref.saas@gmail.com");
        given(fakeDataDao.selectUserByUid(userUidForCur)).willReturn(Optional.of(user));
        given(fakeDataDao.updateUser(user)).willReturn(1);
        ArgumentCaptor<User> captor=ArgumentCaptor.forClass(User.class);
        int updateResult = userService.updateUser(user);
        verify(fakeDataDao).selectUserByUid(userUidForCur);
        verify(fakeDataDao).updateUser(captor.capture());
        User user1=captor.getValue();
        userFiledCheck(user1);
        assertThat(updateResult).isEqualTo(1);

    }

    @Test
    void ShouldremoveUser() {
        UUID userUidForCur = UUID.randomUUID();
        User user = new User(userUidForCur, "Aref", "Soheily"
                , User.Gender.Male, 31, "aref.saas@gmail.com");
        given(fakeDataDao.selectUserByUid(userUidForCur)).willReturn(Optional.of(user));
        given(fakeDataDao.deleteUser(userUidForCur)).willReturn(1);

        int deleteResult = userService.removeUser(userUidForCur);
        verify(fakeDataDao).selectUserByUid(userUidForCur);
        verify(fakeDataDao).deleteUser(userUidForCur);

        assertThat(deleteResult).isEqualTo(1);
    }
    @Test
    void ShouldinsertUser() {

        User user = new User(null, "Aref", "Soheily"
                , User.Gender.Male, 31, "aref.saas@gmail.com");
        given(fakeDataDao.insertUser(any(UUID.class),eq(user))).willReturn(1);
        ArgumentCaptor<User> captor=ArgumentCaptor.forClass(User.class);
        int insertResult = userService.InsertUser(user);
        verify(fakeDataDao).insertUser(any(UUID.class),captor.capture());
        User user1=captor.getValue();
        userFiledCheck(user1);
        assertThat(insertResult).isEqualTo(1);
    }
}