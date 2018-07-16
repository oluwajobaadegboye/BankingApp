package edu.mum.cs.bankingapp.service;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.dao.UserDao;
import edu.mum.cs.bankingapp.model.ErrorMessage;
import edu.mum.cs.bankingapp.model.Response;
import edu.mum.cs.bankingapp.model.User;

import java.util.List;

public class UserService {

    UserDao userDao = null;

    public UserService(MongoClient mongoClient){
        userDao = new UserDao(mongoClient);
    }

    public Response createUser(User user) {
        user =  userDao.createUser(user);
        Response response = new Response();
        if(user == null){
            response.setResponseCode(ErrorMessage.UNABLE_TO_CREATE_USER.getResponseCode());
            response.setResponseMessage(ErrorMessage.UNABLE_TO_CREATE_USER.getResponseMessage());
        }else{
            //TODO: create account
            response.setResponseCode(ErrorMessage.SUCCESSFUL.getResponseCode());
            response.setResponseMessage(ErrorMessage.SUCCESSFUL.getResponseMessage());
            response.setUser(user);
        }
        return response;
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
