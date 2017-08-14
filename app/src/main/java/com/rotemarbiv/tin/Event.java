package com.rotemarbiv.tin;

/**
 * Created by dafnaarbiv on 23/07/2017.
 */

public class Event {

    public int time ;
    public String address;
    public String walker; // going to be type User
    public boolean isItMe;

    Event( int time , String address, String walker, boolean isItMe){
        this.time = time ;
        this.address = address;
        this.walker = walker;
        this.isItMe = isItMe;
    }


    String getTitle(){
        return walker + "is taking the Dog on " + time;
    }

    @Override
    public String toString(){
        return walker + " " + time;
    }
}
