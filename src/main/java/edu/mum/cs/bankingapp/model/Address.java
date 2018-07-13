package edu.mum.cs.bankingapp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Address extends IDObject {

    @NotNull
    @Size(min = 2)
    private String userId;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;
    @NotNull
    private String zip;

    public Address(String id,String userId,String street,String city,String state,String country,String zip){
        super(id);
        this.userId = userId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
