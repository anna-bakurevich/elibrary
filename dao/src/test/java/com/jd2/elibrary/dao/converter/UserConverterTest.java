package com.jd2.elibrary.dao.converter;

import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserConverterTest {
    @Test
    void toUserNull() {
        final User user = UserConverter.convertToUser(null);
        assertNull(user);
    }

    @Test
    void toUserEntityNull() {
        final UserEntity userEntity = UserConverter.convertToUserEntity(null);
        assertNull(userEntity);
    }

    @Test
    void toListUserNull() {
        final List<User> userList = UserConverter.convertToListUser(null);
        assertNull(userList);
    }

    @Test
    void toUserNotNull() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setRole(Role.LIBRARIAN);
        userEntity.setLastName("Anna");
        userEntity.setFirstName("Bakurevich");
        userEntity.setLogin("Anna");
        userEntity.setPassword("123");

        final User user = UserConverter.convertToUser(userEntity);

        assertNotNull(user);
        assertEquals(user.getId(), userEntity.getId());
        assertEquals(user.getRole(), userEntity.getRole());
        assertEquals(user.getFirstName(), userEntity.getFirstName());
    }

    @Test
    void toUserEntityNotNull() {
        final User user = new User();
        user.setId(1);
        user.setRole(Role.LIBRARIAN);
        user.setLastName("Anna");
        user.setFirstName("Bakurevich");
        user.setLogin("Anna");
        user.setPassword("123");

        final UserEntity userEntity = UserConverter.convertToUserEntity(user);

        assertNotNull(userEntity);
        assertEquals(user.getId(), userEntity.getId());
        assertEquals(user.getFirstName(), userEntity.getFirstName());
        assertEquals(user.getRole(), userEntity.getRole());
    }

    @Test
    void toListUserNotNull() {
        final List<UserEntity> userEntityList = Arrays.asList(new UserEntity(), new UserEntity());

        final List<User> userList = UserConverter.convertToListUser(userEntityList);
        assertNotNull(userList);
        assertEquals(userEntityList.size(), userList.size());
    }
}
