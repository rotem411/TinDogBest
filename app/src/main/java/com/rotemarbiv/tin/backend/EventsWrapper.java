package com.rotemarbiv.tin.backend;

import java.util.ArrayList;
import java.util.List;

import static com.rotemarbiv.tin.backend.Util.checkNotNull;

/**
 * Created by Omri on 9/2/2017
 */

public class EventsWrapper {

    private List<Event> events = new ArrayList<>();

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        checkNotNull(event, "'event' was null");
        events.add(event);
    }

    public void removeEvent(Event event) {
        checkNotNull(event, "'event' was null");
        events.remove(event);
    }

}
