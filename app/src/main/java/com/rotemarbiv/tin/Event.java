package com.rotemarbiv.tin;

/**
 * Created by dafnaarbiv on 23/07/2017.
 */

public class Event {

    public int time ;
    public String address;
    public String walker; // going to be type User
    public boolean isItMe;
    public String dogName;

    Event( int time , String address, String walker, boolean isItMe, String dogName){
        this.time = time ;
        this.address = address;
        this.walker = walker;
        this.isItMe = isItMe;
        this.dogName = dogName;
    }


    String getTitle(){
        return walker + "is taking "+ dogName +" on " + time;
    }

    @Override
    public String toString(){
        return walker + " " + time;
    }
}
