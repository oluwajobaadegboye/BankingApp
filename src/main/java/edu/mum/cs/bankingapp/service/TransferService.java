package edu.mum.cs.bankingapp.service;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.dao.AccountDao;
import edu.mum.cs.bankingapp.dao.TransferDao;
import edu.mum.cs.bankingapp.dao.UserDao;
import edu.mum.cs.bankingapp.model.Account;
import edu.mum.cs.bankingapp.model.Response;
import edu.mum.cs.bankingapp.model.Transfer;
import edu.mum.cs.bankingapp.model.User;

public class TransferService {

    private TransferDao transferDao;
    private AccountDao accountDao;

    public TransferService(MongoClient mongoClient) {
        transferDao = new TransferDao(mongoClient);
        accountDao = new AccountDao(mongoClient);
    }

    public Response doTransfer(Transfer transfer, User debitingUser) {
        Account debitingAccount = accountDao.findAccountByUserId(debitingUser.getId());
        return transferDao.createTransfer(transfer,debitingAccount);
    }
}
