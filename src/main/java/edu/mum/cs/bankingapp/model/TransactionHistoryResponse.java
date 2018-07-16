package edu.mum.cs.bankingapp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class TransactionHistoryResponse extends IDObject{
    @NotNull
    private User user;
    @NotNull
    private double transactionAmount;

    @Size(min = 2)
    private String recipient;
    @NotNull
    private String transactionType;
    @NotNull
    private BillPayment billPayment;
    @NotNull
    private Date transactionDate;

    public TransactionHistoryResponse(){
        super("");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public BillPayment getBillPayment() {
        return billPayment;
    }

    public void setBillPayment(BillPayment billPayment) {
        this.billPayment = billPayment;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "TransactionHistoryResponse{" +
                "user=" + user +
                ", transactionAmount=" + transactionAmount +
                ", recipient='" + recipient + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", billPayment=" + billPayment +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
