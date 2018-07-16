package edu.mum.cs.bankingapp.service;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.dao.AccountDao;
import edu.mum.cs.bankingapp.model.Account;

import java.util.List;

public class AccountService {

    AccountDao accountDao;

    public AccountService(MongoClient mongoClient) {
        accountDao = new AccountDao(mongoClient);
    }

    public Account createAccount(Account account) {
        return accountDao.creatAccount(account);
    }

    public List<Account> retrieveAllAccount() {
        return accountDao.retrieveAllAccount();
    }

    public Account findAccountByUserId(String userId) {
        return accountDao.findAccountByUserId(userId);
    }

    public Account findAccountByAccountId(String accountId) {
        return accountDao.findAccountByAccountId(accountId);
    }

    public Account findAccountByNumber(Integer accountNumber) {
        return accountDao.findAccountByNumber(accountNumber);
    }

    public Account findLastAccount() {
        return accountDao.findLastAccount();
    }

    public void updateAccount(Account account) {
         accountDao.updateAccount(account);
    }
}
