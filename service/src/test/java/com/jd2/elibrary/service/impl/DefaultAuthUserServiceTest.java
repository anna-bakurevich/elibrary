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
import static org.mockito.Mockito.verify;
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
    void loginTest() {
        when(dao.getByLogin("Anna")).thenReturn(new User(null, null, null, "Anna","123"));
        User user = service.login("Anna", "123");
        assertNotNull(user);
        when(dao.getByLogin("Ivan")).thenReturn(null);
        User user1 = service.login("Ivan", "123");
        assertNull(user1);
    }

    @Test
    void getIdByLoginTest(){
        when(dao.getIdByLogin("Anna")).thenReturn(1);
        assertEquals(1, service.getIdByLogin("Anna"));
    }

    @Test
    void getByIdTest(){
        User user = new User();
        user.setId(1);
        when((dao.getById(1))).thenReturn(user);
        assertNotNull(service.id(1));
    }

    @Test
    void saveUserTest(){
        User user = new User();
        service.saveUser(user);
        verify(dao).saveUser(user);
    }

    @Test
    void removeUserTest(){
        service.removeUser(1);
        verify(dao).removeUser(1);
    }

    @Test
    void loginIsExistTest(){
        User user = new User();
        user.setId(100);
        user.setLogin("123");
        when(dao.getIdByLogin("123")).thenReturn(100);
        assertTrue(service.loginIsExist("123"));
        assertFalse(service.loginIsExist("321"));
    }

@Test
    void idIsExistTest(){
    User user = new User();
    user.setId(100);
    when(dao.findById(100)).thenReturn(true);
    assertTrue(service.idIsExist(100));
    assertFalse(service.idIsExist(1000));
}
}
