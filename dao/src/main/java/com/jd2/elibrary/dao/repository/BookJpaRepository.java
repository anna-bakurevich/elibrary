package com.jd2.elibrary.dao.repository;

import com.jd2.elibrary.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookJpaRepository extends JpaRepository<BookEntity, Integer> {
    BookEntity findByIsbn(String isbn);

    @Transactional
    @Modifying
    @Query(value = "update BookEntity as be set be.count = :count where be.id = :id")
    void updateCount(@Param("id") int id, @Param("count") int count);

}
