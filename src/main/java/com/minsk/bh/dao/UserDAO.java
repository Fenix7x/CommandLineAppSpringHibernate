package com.minsk.bh.dao;

import com.minsk.bh.entity.User;

import java.util.List;

public interface UserDAO {

    void registration(User user);

    User login(User user);

    List<User> getAllUsers();
}
