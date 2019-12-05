package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.dao.converter.UserConverter;
import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.dao.repository.UserJpaRepository;
import com.jd2.elibrary.model.User;

import java.util.List;


public class DefaultUserDao implements UserDao {
     private final UserJpaRepository userJpaRepository;

    public DefaultUserDao(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public int save(User user) {
        UserEntity userEntity = UserConverter.convertToUserEntity(user);
        return userJpaRepository.save(userEntity).getId();
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> users = userJpaRepository.findAll();
        return UserConverter.convertToListUser(users);
    }

    @Override
    public User findByLogin(String login) {
        UserEntity userEntity = userJpaRepository.findByLogin(login);
        return UserConverter.convertToUser(userEntity);
    }

    @Override
    public User findById(int id) {
        UserEntity userEntity = userJpaRepository.findById(id).get();
        return UserConverter.convertToUser(userEntity);
    }

    @Override
    public int getIdByLogin(String login) {
        return userJpaRepository.getIdByLogin(login);
    }

    @Override
    public boolean existsById(int id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public void update(User user, String firstName, String lastName, String phone) {
        UserEntity userEntity = UserConverter.convertToUserEntity(user);
        userJpaRepository.update(userEntity.getId(), firstName, lastName, phone);
    }

    @Override
    public void deleteById(int id) {
        userJpaRepository.deleteById(id);
    }
}
