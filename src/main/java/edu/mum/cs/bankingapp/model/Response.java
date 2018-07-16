package edu.mum.cs.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private String responseCode;
    private String responseMessage;
    private Account account;
    private User user;
    List<TransactionHistoryResponse> transactionHistoryList = new ArrayList<>();

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<TransactionHistoryResponse> getTransactionHistoryList() {
        return transactionHistoryList;
    }

    public void setTransactionHistoryList(List<TransactionHistoryResponse> transactionHistoryList) {
        this.transactionHistoryList = transactionHistoryList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Response{" +
                "responseCode='" + responseCode + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", account=" + account +
                ", user=" + user +
                ", transactionHistoryList=" + transactionHistoryList +
                '}';
    }
}
