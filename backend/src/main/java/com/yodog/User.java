package com.yodog;

/**
 * Created by Omri on 8/15/2017
 */

public class User {
    private String name;
    private String password;
    private float rating;
    private String phoneNumber;
    private Address address;
    private Dog dog;

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
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
