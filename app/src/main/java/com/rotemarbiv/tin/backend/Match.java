package com.rotemarbiv.tin.backend;

import java.io.Serializable;

import static com.rotemarbiv.tin.backend.Util.checkNotNull;

/**
 * Created by Omri on 9/2/2017
 */

public class Match {

    private Event userEvent;
    private Event dogEvent;

    private Match(Event userEvent, Event dogEvent) {
        this.userEvent = userEvent;
        this.dogEvent = dogEvent;
    }

    public static Match createMatch(Event userEvent, Event dogEvent) {
        checkNotNull(userEvent, "'userEvent' was null");
        checkNotNull(dogEvent, "'dogEvent' was null");
        return new Match(userEvent, dogEvent);
    }

    public Event getUserEvent() {
        return userEvent;
    }

    public Event getDogEvent() {
        return dogEvent;
    }

}
