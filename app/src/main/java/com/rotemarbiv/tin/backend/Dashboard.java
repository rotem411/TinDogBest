package com.rotemarbiv.tin.backend;

import java.util.ArrayList;

import static com.rotemarbiv.tin.backend.Util.checkNotNull;

/**
 * Created by Omri on 8/16/2017
 */

public class Dashboard {

    private ArrayList<Event> userEvents = new ArrayList<>();
    private ArrayList<Event> dogEvents = new ArrayList<>();
    private ArrayList<Event> pendingEvents = new ArrayList<>();

    public ArrayList<Event> getUserEvents() {
        return userEvents;
    }

    public ArrayList<Event> getDogEvents() {
        return dogEvents;
    }

    public ArrayList<Event> getPendingEvents() {
        return pendingEvents;
    }

    public void addUserEvent(Event event) {
        checkNotNull(event, "'event' was null");
        userEvents.add(event);
    }

    public void removeUserEvent(Event event) {
        checkNotNull(event, "'event' was null");
        userEvents.remove(event);
    }

    public void addDogEvent(Event event) {
        checkNotNull(event, "'event' was null");
        dogEvents.add(event);
    }

    public void removeDogEvent(Event event) {
        checkNotNull(event, "'event' was null");
        dogEvents.remove(event);
    }

    public void addPendingEvent(Event event) {
        checkNotNull(event, "'event' was null");
        pendingEvents.add(event);
    }

    public void removePendingEvent(Event event) {
        checkNotNull(event, "'event' was null");
        pendingEvents.remove(event);
    }
}
