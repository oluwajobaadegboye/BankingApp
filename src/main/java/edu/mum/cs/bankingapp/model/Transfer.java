package edu.mum.cs.bankingapp.model;

import java.time.LocalDate;

public class Transfer extends IDObject {
    private Integer accountNumber;
    private String recipientName;
    private LocalDate transactionDate;
    private String narration;
    private Double transferAmount;

    private Transfer() {
        super("");
    }

    public Transfer(String id, Integer accountNumber, String recipientName, LocalDate transactionDate,
                    String narration, Double transferAmount) {
        super(id);
        this.accountNumber = accountNumber;
        this.recipientName = recipientName;
        this.transactionDate = transactionDate;
        this.narration = narration;
        this.transferAmount = transferAmount;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "accountNumber=" + accountNumber +
                ", recipientName='" + recipientName + '\'' +
                ", transactionDate=" + transactionDate +
                ", narration='" + narration + '\'' +
                ", transferAmount=" + transferAmount +
                '}';
    }
}
