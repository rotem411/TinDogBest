package com.rotemarbiv.tin.backend;

import static com.rotemarbiv.tin.backend.Util.checkNotNull;

/**
 * Created by Omri on 8/24/2017
 */

public class EventTime {

    private String date;
    private String time;

    private EventTime(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public static EventTime createEventTime(String date, String time) {
        checkNotNull(date, "'date' was null");
        checkNotNull(time, "'time' was null");
        return new EventTime(date, time);
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}
