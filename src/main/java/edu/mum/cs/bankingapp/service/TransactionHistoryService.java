package edu.mum.cs.bankingapp.service;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.dao.TransactionHistoryDao;
import edu.mum.cs.bankingapp.model.TransactionHistory;
import edu.mum.cs.bankingapp.model.TransactionHistoryResponse;
import edu.mum.cs.bankingapp.model.User;

import java.time.LocalDate;
import java.util.List;

public class TransactionHistoryService {
    private TransactionHistoryDao transactionHistoryDao;
    public TransactionHistoryService(MongoClient client){
        transactionHistoryDao = new TransactionHistoryDao(client);
    }

    public List<TransactionHistoryResponse> findAllHistory(User user, LocalDate startDate,LocalDate endDate) {
        return transactionHistoryDao.findAllHistory(user,startDate,endDate);
    }

    public TransactionHistory createHistory(TransactionHistory history){
        return transactionHistoryDao.createHistory(history);
    }
}
