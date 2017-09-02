package com.rotemarbiv.tin.backend;

import java.util.ArrayList;
import java.util.List;

import static com.rotemarbiv.tin.backend.Util.checkNotNull;

/**
 * Created by Omri on 8/16/2017
 */

public class Dashboard {

    private ArrayList<Event> userEvents = new ArrayList<>();
    private ArrayList<Event> dogEvents = new ArrayList<>();
    private ArrayList<Event> pendingEvents = new ArrayList<>();

    Dashboard() {
    }

    public ArrayList<Event> getUserEvents() {
        return userEvents;
    }

    public ArrayList<Event> getDogEvents() {
        return dogEvents;
    }

    public ArrayList<Event> getPendingEvents() {
        return pendingEvents;
    }

    private void addUserEvent(Event event) {
        addEventToList(event, userEvents);
    }

    private void addDogEvent(Event event) {
        addEventToList(event, dogEvents);
    }

    private void addPendingEvent(Event event) {
        addEventToList(event, pendingEvents);
    }

    private void addEventToList(Event event, List<Event> list) {
        checkNotNull(event, "'event' was null");
        list.add(event);
    }



}
