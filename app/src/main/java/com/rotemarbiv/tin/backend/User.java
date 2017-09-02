package com.rotemarbiv.tin.backend;

import java.util.ArrayList;

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
    private  String dogName;
    private String dogSize;
    private Dashboard dashboard = new Dashboard();
    private int photo;

    public static User of(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setPassword("password");
        return user;
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
}
