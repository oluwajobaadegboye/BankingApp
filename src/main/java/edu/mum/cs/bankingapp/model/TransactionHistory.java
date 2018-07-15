package edu.mum.cs.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionHistory extends IDObject {

    @NotNull
    private String userId;
    @NotNull
    private double transactionAmount;
    @Size(min = 2)
    private String recipient;
    @NotNull
    private String transactionType;
    @NotNull
    private String billPaymentId;

    public TransactionHistory(String id, String userId,double transactionAmount,
                              String recipient,String transactionType,String billPaymentId) {
        super(id);
        this.userId = userId;
        this.transactionAmount = transactionAmount;
        this.recipient = recipient;
        this.transactionType = transactionType;
        this.billPaymentId = billPaymentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }


    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getBillPaymentId() {
        return billPaymentId;
    }

    public void setBillPaymentId(String billPaymentId) {
        this.billPaymentId = billPaymentId;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "userId='" + userId + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", recipient='" + recipient + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", billPaymentId='" + billPaymentId + '\'' +
                '}';
    }
}
