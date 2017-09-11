package com.rotemarbiv.tin.backend;

import java.util.ArrayList;

import static com.rotemarbiv.tin.backend.Util.checkNotNull;

/**
 * Created by Omri on 8/15/2017
 */

public class User {
    private String name;
    private String password;
    private ArrayList<Integer> rates = new ArrayList<>();

    private String phoneNumber;
    private Address address;
    private String email;
    private String dogName;
    private String dogSize;
    private Dashboard dashboard = new Dashboard();
    private int photo;

    public static User of(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword("password");
        user.dogName = "No Dog";
        user.dogSize = "S";
        return user;
    }

    public User() {
    }

    private User(String name,
                 String password,
                 String phoneNumber,
                 Address address,
                 String email,
                 String dogName,
                 String dogSize) {
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.dogName = dogName;
        this.dogSize = dogSize;
    }
    public static User createUser(String name,
                                  String password,
                                  String phoneNumber,
                                  Address address,
                                  String email,
                                  String dogName,
                                  String dogSize) {
        verifyParamsNotNull(name, password, phoneNumber, address, email, dogName, dogSize);
        User toReturn = new User(name, password, phoneNumber, address, email, dogName, dogSize);
        return toReturn;
    }
    public static User createUser(String name,
                                  String password,
                                  String phoneNumber,
                                  Address address,
                                  String email,
                                  String dogName,
                                  String dogSize,
                                  ArrayList<Integer> rates) {
        verifyParamsNotNull(name, password, phoneNumber, address, email, dogName, dogSize);
        User toReturn = new User(name, password, phoneNumber, address, email, dogName, dogSize);
        if(rates != null) {
            toReturn.setRates(rates);
        }
        return toReturn;
    }

    private static void verifyParamsNotNull(String name, String password, String phoneNumber, Address address, String email, String dogName, String dogSize) {
        checkNotNull(name, "'name' was null");
        checkNotNull(password, "'password' was null");
        checkNotNull(phoneNumber, "'phoneNumber' was null");
        checkNotNull(address, "'address' was null");
        checkNotNull(email, "'email' was null");
        checkNotNull(dogName, "'dogName' was null");
        checkNotNull(dogSize, "'dogSize' was null");

    }

    public static User createUser(String name,
                                  String password,
                                  String phoneNumber,
                                  String addressDescription,
                                  String email,
                                  String dogName,
                                  String dogSize) {
        verifyParamsAreNotNull(name, password, phoneNumber, addressDescription, email, dogName, dogSize);
        Address address = Address.createAddress(addressDescription);
        return new User(name, password, phoneNumber, address, email, dogName, dogSize);
    }

    private static void verifyParamsAreNotNull(String name, String password, String phoneNumber, String addressDescription, String email, String dogName, String dogSize) {
        checkNotNull(name, "'name' was null");
        checkNotNull(password, "'password' was null");
        checkNotNull(phoneNumber, "'phoneNumber' was null");
        checkNotNull(addressDescription, "'addressDescription' was null");
        checkNotNull(email, "'email' was null");
        checkNotNull(dogName, "'dogName' was null");
        checkNotNull(dogSize, "'dogSize' was null");
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getRating() {
        float sum = 0.0f;
        for (Integer integer : rates) {
            sum += integer;
        }
        return sum / rates.size();
    }

    public int getRatersCount() {
        return rates.size();
    }

    public void rate(int rate) {
        this.rates.add(rate);
    }

    public ArrayList<Integer> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Integer> rates) {
        this.rates = rates;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDogName() {
        return this.dogName;
    }

    public void setDogName(String name) {
        this.dogName = name;
    }

    public String getDogSize() {
        return this.dogSize;
    }

    public void setDogSize(String size) {
        this.dogSize = size;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address.getDescription();
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address.setDescription(address);
    }

    public int getPhoto() {
        return photo;
    }
}
