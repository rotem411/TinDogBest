package com.rotemarbiv.tin.backend;

/**
 * Created by Omri on 8/16/2017
 */

public class Dashboard {

    private EventsWrapper userEvents = new EventsWrapper();
    private EventsWrapper dogEvents = new EventsWrapper();
    private EventsWrapper pendingEvents = new EventsWrapper();

    public EventsWrapper getUserEvents() {
        return userEvents;
    }

    public EventsWrapper getDogEvents() {
        return dogEvents;
    }

    public EventsWrapper getPendingEvents() {
        return pendingEvents;
    }
}
