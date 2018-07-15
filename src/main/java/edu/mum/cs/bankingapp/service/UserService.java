package edu.mum.cs.bankingapp.service;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.dao.UserDao;
import edu.mum.cs.bankingapp.model.User;

import java.util.List;

public class UserService {

    UserDao userDao = null;

    public UserService(MongoClient mongoClient){
        userDao = new UserDao(mongoClient);
    }

    public User createUser(User user) {
        return userDao.createUser(user);
    }

    public User retrieveUserById(String userId) {
        return userDao.retrieveUserById(userId);
    }

    public User retrieveUserByEmail(String email) {
        return userDao.retrieveUserByEmail(email);
    }

    public User retrieveUserByMobile(String mobile) {
        return userDao.retrieveUserByMobile(mobile);
    }

    public User retrieveUserByUsername(String userName) {
        return userDao.retrieveUserByUsername(userName);
    }

    public List<User> retrieveAllUser() {
        return userDao.retrieveAllUser();
    }
}
