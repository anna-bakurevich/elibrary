package com.jd2.elibrary.dao.converter;

import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    public static UserEntity convertToUserEntity(User user) {
        if (user == null) {
            return null;
        }
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        return userEntity;
    }

    public static User convertToUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        final User user = new User();
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhone(userEntity.getPhone());
        user.setLogin(userEntity.getLogin());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        return user;
    }

    public static List<User> convertToListUser(List<UserEntity> userEntities) {
        if (userEntities == null){
            return null;
        }
        final List<User> users = new ArrayList<>();
        for (UserEntity ue : userEntities) {
            users.add(convertToUser(ue));
        }
        return users;
    }
}
