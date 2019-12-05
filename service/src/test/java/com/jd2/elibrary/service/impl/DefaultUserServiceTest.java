package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.impl.DefaultUserService;
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
public class DefaultUserServiceTest {
    @Mock
    UserDao dao;

    @InjectMocks
    DefaultUserService service;

    @Test
    void findAllTest() {
        when(dao.findAll()).thenReturn(new ArrayList<User>());
        List<User> users = service.findAll();
        assertNotNull(users);
    }
    @Test
    void loginTest() {
        when(dao.findByLogin("Anna")).thenReturn(new User(null, null, null, "Anna","123"));
        User user = service.login("Anna", "123");
        assertNotNull(user);
        when(dao.findByLogin("Ivan")).thenReturn(null);
        User user1 = service.login("Ivan", "123");
        assertNull(user1);
    }

    @Test
    void getIdByLoginTest(){
        when(dao.getIdByLogin("Anna")).thenReturn(1);
        assertEquals(1, service.getIdByLogin("Anna"));
    }

    @Test
    void findByIdTest(){
        User user = new User();
        user.setId(1);
        when((dao.findById(1))).thenReturn(user);
        assertNotNull(service.findById(1));
    }

    @Test
    void saveTest(){
        User user = new User();
        service.save(user);
        verify(dao).save(user);
    }

    @Test
    void deleteByIdTest(){
        service.deleteById(1);
        verify(dao).deleteById(1);
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
    void existByIdTest(){
    User user = new User();
    user.setId(100);
    when(dao.existsById(100)).thenReturn(true);
    assertTrue(service.existsById(100));
    assertFalse(service.existsById(1000));
}
}
