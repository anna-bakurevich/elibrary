package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.impl.DefaultAuthUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultAuthUserServiceTest {
    @Mock
    UserDao dao;

    @InjectMocks
    DefaultAuthUserService service;

    @Test
    void getUsersTest() {
        when(dao.getUsers()).thenReturn(new ArrayList<User>());
        List<User> users = service.getUsers();
        assertNotNull(users);
    }
    @Test
    void getByLoginTest() {
        when(dao.getByLogin("Anna")).thenReturn(new User(100, null, null, null,"Anna", "123") );
        User user = service.login("Anna", "123");
        assertNotNull(user);
    }
}
