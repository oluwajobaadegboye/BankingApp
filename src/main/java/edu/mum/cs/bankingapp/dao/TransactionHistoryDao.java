package edu.mum.cs.bankingapp.dao;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.converter.GenericConverter;
import edu.mum.cs.bankingapp.model.BillPayment;
import edu.mum.cs.bankingapp.model.TransactionHistory;
import edu.mum.cs.bankingapp.model.TransactionHistoryResponse;
import edu.mum.cs.bankingapp.model.User;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryDao {
    private DBCollection dbCollection;
    private UserDao userDao;
    private BillPaymentDao billPaymentDao;

    public TransactionHistoryDao(MongoClient client){
        this.dbCollection = client.getDB("bankDB").getCollection("transactionHistory");
        this.userDao = new UserDao(client);
        this.billPaymentDao = new BillPaymentDao(client);
    }

    public List<TransactionHistoryResponse> findAllHistory(User user, LocalDate startDate, LocalDate endDate) {
        List<TransactionHistoryResponse> transactionHistoryResponseList = new ArrayList<>();
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            TransactionHistoryResponse account = toTransactionHistoryResponse(doc);
            transactionHistoryResponseList.add(account);
        }
        return transactionHistoryResponseList;
    }

    private TransactionHistoryResponse toTransactionHistoryResponse(DBObject doc) {
        if (doc == null) {
            return null;
        }
        TransactionHistoryResponse object = new TransactionHistoryResponse();
        object.setRecipient((String) doc.get("recipient"));
        object.setTransactionAmount((Double)doc.get("transactionAmount"));
        object.setTransactionType((String) doc.get("transactionType"));
        object.setTransactionDate((LocalDate) doc.get("transactionDate"));

        String userId = (String) doc.get("userId");
        String billpaymentId =(String) doc.get("billPaymentId");

        if(userId!=null && !"".equals(userId)){
            User user = userDao.retrieveUserById(userId);
            object.setUser(user);
        }
        if(billpaymentId!=null && !"".equals(billpaymentId)){
            BillPayment billPayment = billPaymentDao.retrieveBillPaymentById(billpaymentId);
            object.setBillPayment(billPayment);
        }
        ObjectId id = (ObjectId) doc.get("_id");
        object.setId(id.toString());
        return object;
    }

    public TransactionHistory createHistory(TransactionHistory history) {
        DBObject dbObject = GenericConverter.toDBObject(history, TransactionHistory.class);
        this.dbCollection.insert(dbObject);
        ObjectId id = (ObjectId) dbObject.get("_id");
        history.setId(id.toString());
        return history;
    }
}
