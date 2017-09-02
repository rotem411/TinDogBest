package com.rotemarbiv.tin;

/**
 * Created by dafnaarbiv on 22/07/2017.
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by laurescemama on 12/07/2017.
 */

public class User implements Serializable {

    private String fullName;
    public String password;
    public String dogName;
    public String dogSize;
    public String address;
    public String phoneNumber;
    public String email;
    public float rating;
    public boolean notifications;

    public ArrayList<Integer> rates = new ArrayList<Integer>();

    public ArrayList<Event> pendingEvents;

    User(String email, String password, boolean notifications){
        this.email = email;
        this.password = password;
        this.notifications = notifications;

    }

    User(String fullName, String dogName, String address, boolean notifications){
        this.fullName = fullName;
        this.dogName = dogName;
        this.address = address;
        this.notifications = notifications;


        pendingEvents = new ArrayList<Event>();
    }

    User(String fullName, String password, String dogName, String dogSize, String address,
         String phoneNumber, String email,  boolean notifications){

        this.fullName = fullName;
        this.password = password;
        this.dogName = dogName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;

        this.dogSize = dogSize;

        this.notifications = notifications;

        pendingEvents = new ArrayList<Event>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }
    public String getDogSize() {
        return dogSize;
    }

    public void setDogSize(String dogSize) {
        this.dogSize = dogSize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static User convertBackendUserToUser(com.rotemarbiv.tin.backend.User user){
        User toReturn = new User(user.getName(),user.getPassword(),user.getDogName(),
                user.getDogSize(), user.getAddress(), user.getPhoneNumber(), user.getEmail(), true);
        return toReturn;
    }


}