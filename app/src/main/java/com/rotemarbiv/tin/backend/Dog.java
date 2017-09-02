package com.rotemarbiv.tin.backend;

/**
 * Created by Omri on 9/2/2017
 */

class Dog {

    private String name;

    private String size;

    private Dog(String name, String size) {
        this.name = name;
        this.size = size;
    }

    public static Dog createDog(String name, String size) {
        return new Dog(name, size);
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }
}
