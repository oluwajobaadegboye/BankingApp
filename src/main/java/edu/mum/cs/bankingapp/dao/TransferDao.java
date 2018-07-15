package edu.mum.cs.bankingapp.dao;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.model.*;
import java.util.Date;

public class TransferDao {
    AccountDao accountDao;
    TransactionHistoryDao transactionHistoryDao;

    public TransferDao(MongoClient client) {
        accountDao = new AccountDao(client);
        transactionHistoryDao = new TransactionHistoryDao(client);
    }


    public Response createTransfer(Transfer transfer, Account debitingAccount) {
        Response response = null;
        boolean isDebited = debitAccount(debitingAccount, transfer);
        if (isDebited) {
            creditAccount(transfer);
            response = buildResponse("00", "Sucessful", debitingAccount);
        } else {
            response = buildResponse("09", "Insufficient Balance", debitingAccount);
        }
        return response;
    }

    private TransactionHistory buildTransferHistory(String userId, Transfer transfer, Double transferAmt) {
        TransactionHistory transactionHistory = new TransactionHistory("", userId, transferAmt,
                transfer.getRecipientName(), "Transfer", "", new Date());
        return transactionHistory;
    }

    private boolean debitAccount(Account debitingAccount, Transfer transfer) {
        boolean isDebited = false;
        if (debitingAccount.getBalance() >= transfer.getTransferAmount()) {
            debitingAccount.setBalance(debitingAccount.getBalance() - transfer.getTransferAmount());
            accountDao.updateAccount(debitingAccount);

            TransactionHistory transactionHistory = buildTransferHistory(debitingAccount.getUserId(), transfer, (-1 * transfer.getTransferAmount()));
            transactionHistoryDao.createHistory(transactionHistory);
            isDebited = true;
        }
        return isDebited;
    }

    private void creditAccount(Transfer transfer) {
        Account creditAccount = accountDao.findAccountByNumber(transfer.getAccountNumber());//recipient
        creditAccount.setBalance(creditAccount.getBalance() + transfer.getTransferAmount());
        accountDao.updateAccount(creditAccount);

        TransactionHistory transactionHistory = buildTransferHistory(creditAccount.getUserId(), transfer, transfer.getTransferAmount());
        transactionHistoryDao.createHistory(transactionHistory);
    }

    Response buildResponse(String responseCode, String responseMessage, Account account) {
        Response response = new Response();
        if (responseCode.equals("00")) {
            response.setAccount(account);
            response.setResponseCode(responseCode);
            response.setResponseMessage(responseMessage);
        } else {
            response.setResponseMessage(responseMessage);
            response.setResponseCode(responseCode);
        }
        return response;
    }
}
