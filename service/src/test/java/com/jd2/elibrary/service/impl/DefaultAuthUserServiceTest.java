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
    void loginTest() {
        when(dao.getByLogin("Anna")).thenReturn(new User(100, null, null, null,"Anna", "123") );
        User user = service.login("Anna", "123");
        assertNotNull(user);
        when(dao.getByLogin("Ivan")).thenReturn(null);
        User user1 = service.login("Ivan", "123");
        assertNull(user1);
    }

//    @Test
//    void idIsExistTest(){
//        when(dao.idIsExist(1)).thenReturn(true);
//        User user = new User(1, null, null,null,null,null);
//        assertTrue(service.idIsExist(user.getId()));
//    }

    @Test
    void getIdByLoginTest(){
        when(dao.getIdByLogin("Anna")).thenReturn(1);
        assertEquals(1, service.getIdByLogin("Anna"));
    }

    @Test
    void idTest(){
        when((dao.getById(1))).thenReturn(new User(1, null, null,null,null,null));
        assertNotNull(service.id(1));
    }
}
