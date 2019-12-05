package com.jd2.elibrary.dao.repository;

import com.jd2.elibrary.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByLogin(String login);

    @Transactional
    @Modifying
    @Query(value = "update UserEntity as ue set ue.firstName=:firstname, ue.lastName=:lastname, ue.phone=:phone" +
            " where ue.id=:id")
    void update(@Param("id") int id, @Param("firstname") String firstname, @Param("lastname") String lastname,
                @Param("phone") String phone);

    @Transactional
    @Query(value = "select ue.id from UserEntity as ue where ue.login=:login")
    int getIdByLogin(@Param("login") String login);
}
