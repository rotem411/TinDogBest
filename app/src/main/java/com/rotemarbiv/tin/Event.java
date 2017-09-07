package com.rotemarbiv.tin;

import com.rotemarbiv.tin.backend.EventTime;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dafnaarbiv on 23/07/2017.
 */

public class Event implements Serializable {

    public User walker; //owner
    public User dog;   // partner
    public String time; //Todo: Morning / noon / evening - check if
    public String date; //Todo: DatePicker in new Event - make sure latrer date

    // task time
    public String address;
    public String comments;
    public boolean isItMe; // am I the walker

    public int index;

    Event(User walker, User dog, String time, String date){
        this.walker = walker;
        this.dog = dog;
        this.address = dog.getAddress();
        this.time = time;
        this.date = date ;
    }
    
    Event(User walker, User dog, String time, String date, boolean isItMe, int index){
        this.walker = walker;
        this.dog = dog;
        this.time = time;
        this.date = date ;
        this.address = dog.getAddress();
        this.isItMe = isItMe;
        this.index = index;
    }

//    String getTitle(){
//        return walker + "is taking the Dog on " + date;
//    }

    @Override
    public String toString(){
        return walker + " " + date;
    }

    /***
     * If the selfUser using the app is the walker- the event title should be the dog the selfUser will take
     * out. Otherwise, the name of the selfUser who'll take out your dog.
     * @return String
     */
    public String getEventTitle(){
        if (isItMe){
            return dog.getDogName();
        }
        else{
            return walker.getFullName();
        }
    }
    public int getIcon(){
        // here will import pic icon from server according to selfUser

        if (isItMe){
            return R.mipmap.walk_dog_icon;
        }
        else{
            return R.mipmap.walk_user_icon;

        }
    }

    public String getDateStr()
    {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(selectedYear,selectedMonth,selectedDay);
//        editday.setText(new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime()));
//        String day = new SimpleDateFormat("EEEE").format(calendar.getTime());
// // TODO: change this to the way we want to display a date 
        return date;
    }

    public String getTimeStr(){
            return time;
    }


    public String getComments() {
        return comments;
    }

    public User getWalker() {
        return walker;
    }

    public User getDog() {
        return dog;
    }

    public String getAddress() {
        return address;
    }


    public static Event convertBackendEventToEvent(com.rotemarbiv.tin.backend.Event event){
        User convertWalker = User.convertBackendUserToUser(event.getOwner());
        User convertDog = User.convertBackendUserToUser(event.getPartner());

        Event toReturn = new Event(convertWalker, convertDog, event.getTime(), event.getDate());
        return toReturn;
    }
    
    public static ArrayList<Event> convertBackendEventListToEventList(List<com.rotemarbiv.tin.backend.Event> events){
        ArrayList<Event> toReturn = new ArrayList<Event>();       
        for (com.rotemarbiv.tin.backend.Event event :events ){
            toReturn.add(convertBackendEventToEvent(event));
        }
        return toReturn;
    }

    public static com.rotemarbiv.tin.backend.Event convertEventToBackendEvent(Event event){
        com.rotemarbiv.tin.backend.User convertWalker = User.convertUserToBackendUser(event.getWalker());
        com.rotemarbiv.tin.backend.User convertDog = User.convertUserToBackendUser(event.getDog());
        EventTime eventTime = EventTime.createEventTime(event.getDateStr(), event.getTimeStr());
        com.rotemarbiv.tin.backend.Event toReturn = com.rotemarbiv.tin.backend.Event.createEvent(convertWalker,
                convertDog, eventTime, event.getComments());
        return toReturn;
    }
}
