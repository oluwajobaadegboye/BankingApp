package edu.mum.cs.bankingapp.dao;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.model.*;
import edu.mum.cs.bankingapp.model.ErrorMessage;

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
        Account creditingAccount = accountDao.findAccountByNumber(transfer.getAccountNumber());
        if(debitingAccount.getAccountNumber().equals(creditingAccount.getAccountNumber())){
            response = new Response();
            response.setResponseCode(ErrorMessage.CREDITING_AND_DEBITING_ACCOUNT_SAME.getResponseCode());
            response.setResponseMessage(ErrorMessage.CREDITING_AND_DEBITING_ACCOUNT_SAME.getResponseMessage());
            return response;
        }
        boolean isDebited = debitAccount(debitingAccount, transfer);
        if (isDebited) {
            creditAccount(transfer,creditingAccount);
            response = buildResponse(ErrorMessage.SUCCESSFUL, debitingAccount);
        } else {
            response = buildResponse(ErrorMessage.FAILED, debitingAccount);
        }
        return response;
    }

    private TransactionHistory buildTransferHistory(String userId, Transfer transfer, Double transferAmt) {
        TransactionHistory transactionHistory = new TransactionHistory("", userId, transferAmt,
                transfer.getRecipientName(), "Transfer", "", new Date());
        return transactionHistory;
    }

    private TransactionHistory buildTransferHistoryForBillPayment(String userId, BillPayment billPayment, Double transferAmt) {
        TransactionHistory transactionHistory = new TransactionHistory("", userId, transferAmt,
                billPayment.getName(), "Bill Payment", billPayment.getId(), new Date());
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

    public boolean debitAccountForPayBill(Account debitingAccount, BillPayment billPayment) {
        boolean isDebited = false;
        if (debitingAccount.getBalance() >= billPayment.getAmount()) {
            debitingAccount.setBalance(debitingAccount.getBalance() - billPayment.getAmount());
            accountDao.updateAccount(debitingAccount);

            TransactionHistory transactionHistory = buildTransferHistoryForBillPayment(debitingAccount.getUserId(), billPayment, (-1 * billPayment.getAmount()));
            transactionHistoryDao.createHistory(transactionHistory);
            isDebited = true;
        }
        return isDebited;
    }

    private void creditAccount(Transfer transfer, Account creditingAccount) {
        ;//recipient
        creditingAccount.setBalance(creditingAccount.getBalance() + transfer.getTransferAmount());
        accountDao.updateAccount(creditingAccount);

        TransactionHistory transactionHistory = buildTransferHistory(creditingAccount.getUserId(), transfer, transfer.getTransferAmount());
        transactionHistoryDao.createHistory(transactionHistory);
    }

    Response buildResponse(ErrorMessage message, Account account) {
        Response response = new Response();
        if (message.getResponseCode().equals("00")) {
            response.setAccount(account);
            response.setResponseCode(message.getResponseCode());
            response.setResponseMessage(message.getResponseMessage());
        } else {
            response.setResponseMessage(message.getResponseMessage());
            response.setResponseCode(message.getResponseCode());
        }
        return response;
    }
}
