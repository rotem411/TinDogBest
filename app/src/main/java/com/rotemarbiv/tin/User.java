package com.rotemarbiv.tin;

/**
 * Created by dafnaarbiv on 22/07/2017.
 */

import java.io.Serializable;

/**
 * Created by laurescemama on 12/07/2017.
 */

public class User implements Serializable {

    public String fullName;
    public String userName;
    public String password;
    public String dogName;
    public String dogSize;
    public String address;
    public String phoneNumber;
    public String mail;
    public boolean notifications;
    public int pros;
    public int cons;

    User(String userName, String password, boolean notifications){
        this.userName = userName;
        this.password = password;
        this.notifications = notifications;
        pros = 0;
        cons = 0;
    }

    User(String fullName, String userName, String dogName, String address, boolean notifications){
        this.fullName = fullName;
        this.userName = userName;
        this.dogName = dogName;
        this.address = address;
        this.notifications = notifications;
        pros = 0;
        cons = 0;
    }

    User(String fullName, String userName, String password, String dogName, String dogSize, String address,
         String phoneNumber, String mail,  boolean notifications){

        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.dogName = dogName;
        this.dogSize = dogSize;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;

        this.notifications = notifications;
        pros = 0;
        cons = 0;
    }


}