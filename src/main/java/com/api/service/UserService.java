package com.api.service;

import com.api.dao.UserDao;
import com.api.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public List<User> getUsers() {
        log.info("UserDao: getUsers() to find all users");
        return userDao.findAll();
    }

    public void delete(Integer id) {
        log.info("UserDao: delete() to remove user by id");
        userDao.delete(getUser(id));
    }

    public User getUser(Integer id) {
        log.info("UserDao: getUser() to find user by id");
        return userDao.findById(id).orElse(null);
    }

    public List<User> saveOrUpdate(List<User> user) {
        log.info("UserDao: save() to insert new user");
        return userDao.saveAll(user);
    }

    public List<User> saveOrUpdateAll(List<User> users) {
        log.info("UserDao: save() to insert new user");
        return userDao.saveAll(users);
    }
}
