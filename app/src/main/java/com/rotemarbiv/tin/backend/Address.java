package com.rotemarbiv.tin.backend;

/**
 * Created by Omri on 8/15/2017
 */

public class Address {

    private String description;

    private Address(String description) {
        this.description = description;
    }

    public static Address createAddress(String description) {
        return new Address(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newAddress) {
        description = newAddress;
    }

}
