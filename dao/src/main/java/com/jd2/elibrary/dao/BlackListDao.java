package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.BlackList;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.User;

import java.util.List;

public interface BlackListDao {
    //create
    void saveBlackUser();

    //read
    List<BlackList> getBlackList();
    BlackList getBlackListById(int id);

    //update
    void updateBlackList(int id);

    //delete
    void removeBlackListUser(User user, Book book);

}
