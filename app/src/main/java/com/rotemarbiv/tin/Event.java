package com.rotemarbiv.tin;

/**
 * Created by dafnaarbiv on 23/07/2017.
 */

public class Event {

    public User walker;
    public User dog;
    public String time; //Todo: Morning / noon /evening
    public String date; //Todo: DatePicbker in new Event
    public String address;
    public boolean isItMe; // am I the walker

    Event(User walker, User dog, String date, String time, boolean isItMe){
        this.walker = walker;
        this.dog = dog;
        this.time = time;
        this.date = date ;
        this.address = dog.address;
        this.isItMe = isItMe;
    }

//    String getTitle(){
//        return walker + "is taking the Dog on " + date;
//    }

    @Override
    public String toString(){
        return walker + " " + date;
    }

    /***
     * If the user using the app is the walker- the event title should be the dog the user will take
     * out. Otherwise, the name of the user who'll take out your dog.
     * @return String
     */
    public String getEventTitle(){
        if (isItMe){
            return dog.dogName;
        }
        else{
            return walker.fullName;
        }
    }
    public int getIcon(){
        // here will import pic icon from server according to user

        if (isItMe){
            return R.mipmap.walk_dog_icon;
        }
        else{
            return R.mipmap.walk_user_icon;

        }
    }

    public String getDateStr(){
        return date;
    }

    public String getTimeStr(){
            return time;
    }
}
