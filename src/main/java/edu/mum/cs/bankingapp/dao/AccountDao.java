package edu.mum.cs.bankingapp.dao;

import com.mongodb.*;
import edu.mum.cs.bankingapp.converter.GenericConverter;
import edu.mum.cs.bankingapp.model.Account;
import edu.mum.cs.bankingapp.model.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private static final Integer DEFAULT_ACCOUNT_NUMBER = 43355250;

    private DBCollection dbCollection;

    public AccountDao(MongoClient mongo) {
        this.dbCollection = mongo.getDB("bankDB").getCollection("accounts");
    }

    public Account creatAccount(Account account) {
        DBObject dbObject = GenericConverter.toDBObject(account, Account.class);
        Account lastAccount = findLastAccount();
        ((BasicDBObject) dbObject).append("accountNumber", lastAccount.getAccountNumber() + 1);
        this.dbCollection.insert(dbObject);
        ObjectId id = (ObjectId) dbObject.get("_id");
        account.setId(id.toString());
        return account;
    }

    public List<Account> retrieveAllAccount() {
        List<Account> data = new ArrayList<Account>();
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Account account = GenericConverter.toAccount(doc);
            data.add(account);
        }
        return data;
    }

    public Account findAccountByUserId(String userId) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("userId", userId).get();
        DBObject data = this.dbCollection.findOne(query);
        Account account = GenericConverter.toAccount(data);
        return account;
    }

    public Account findAccountByAccountId(String accountId) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(accountId)).get();
        DBObject data = this.dbCollection.findOne(query);
        Account account = GenericConverter.toAccount(data);
        return account;
    }

    public Account findAccountByNumber(Integer accountNumber) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("accountNumber", accountNumber).get();
        DBObject data = this.dbCollection.findOne(query);
        Account account = null;
        if (data != null) {
            account = GenericConverter.toAccount(data);
        }
        return account;
    }

    public Account findLastAccount() {
        List<Account> accounts = retrieveAllAccount();
        Account account = new Account("");
        if (account != null && accounts.size() > 0) {
            account = accounts.get(accounts.size() - 1);
        } else {
            account.setAccountNumber(DEFAULT_ACCOUNT_NUMBER);
            account.setAccountType("CHECKING");
        }
        return account;
    }

    public void updateAccount(Account account) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(account.getId())).get();
        this.dbCollection.update(query, GenericConverter.toDBObject(account,Account.class));
    }
}
