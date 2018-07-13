package edu.mum.cs.bankingapp.model;

import java.util.List;

public class User extends IDObject{
    private String name;
    private String userName;
    private String password; // base64 encoded string
    private String email;
    private String mobile;
    private Address address;

    public User(String id,String name,String userName,String password,String email,String mobile,Address address){
        super(id);
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
