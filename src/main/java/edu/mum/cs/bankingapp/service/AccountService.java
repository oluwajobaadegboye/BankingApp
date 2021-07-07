package edu.mum.cs.bankingapp.service;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.dao.AccountDao;
import edu.mum.cs.bankingapp.dao.UserDao;
import edu.mum.cs.bankingapp.model.Account;
import edu.mum.cs.bankingapp.model.ErrorMessage;
import edu.mum.cs.bankingapp.model.Response;
import edu.mum.cs.bankingapp.model.User;

import java.util.List;

public class AccountService {

    AccountDao accountDao;
    UserDao userDao;

    public AccountService(MongoClient mongoClient) {
        accountDao = new AccountDao(mongoClient);
        userDao = new UserDao(mongoClient);
    }

    public Account createAccount(Account account) {
        return accountDao.creatAccount(account);
    }

    public List<Account> retrieveAllAccount() {
        return accountDao.retrieveAllAccount();
    }

    public Response findAccountByUserId(String userId) {
        Account account = accountDao.findAccountByUserId(userId);
        Response response = buildResponse(account);
        return response;
    }


    public Response findAccountByAccountId(String accountId) {
        Account account = accountDao.findAccountByAccountId(accountId);
        Response response = buildResponse(account);
        return response;
    }

    public Response findAccountByNumber(Integer accountNumber) {
        Account account = accountDao.findAccountByNumber(accountNumber);
        Response response = buildResponse(account);
        return response;
    }

    public Account findLastAccount() {
        return accountDao.findLastAccount();
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    private Response buildResponse(Account account){
        Response response = new Response();
        if (account != null) {
            User user = userDao.retrieveUserById(account.getUserId());
            response.setResponseCode(ErrorMessage.SUCCESSFUL.getResponseCode());
            response.setResponseMessage(ErrorMessage.SUCCESSFUL.getResponseMessage());
            response.setAccount(account);
            if (user != null) {
                response.setUser(user);
            }
        } else {
            response.setResponseCode(ErrorMessage.INVALID_ACCOUNT.getResponseCode());
            response.setResponseMessage(ErrorMessage.INVALID_ACCOUNT.getResponseMessage());
        }
        return response;
    }
}
