package edu.mum.cs.bankingapp.model;

public enum  ErrorMessage {
    SUCCESSFUL("00","Successful"),
    FAILED("01", "Insufficient Balance"),
    INVALID_ACCOUNT("02","Account Does not Exist"),
    INVALID_ACCOUNT_INPUT("03","Invalid Account Entered"),
    UNABLE_TO_CREATE_USER("04","User creation failed");

    private String responseCode;
    private String responseMessage;

    ErrorMessage(String responseCode,String responseMessage){
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

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
}
