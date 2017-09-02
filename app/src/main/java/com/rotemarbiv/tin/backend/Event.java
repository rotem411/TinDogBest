package com.rotemarbiv.tin.backend;

import static com.rotemarbiv.tin.backend.Util.checkNotNull;

/**
 * Created by Omri on 9/2/2017
 */

class Event {

    private User owner;
    private User partner;
    private EventTime eventTime;
    private String comments;

    private Event(User owner, User partner, EventTime eventTime, String comments) {
        this.owner = owner;
        this.partner = partner;
        this.eventTime = eventTime;
        this.comments = comments;
    }

    public static Event createEvent(User owner, User partner, EventTime eventTime, String comments) {
        checkNotNull(owner, "'owner' was null");
        checkNotNull(partner, "'partner' was null");
        checkNotNull(eventTime, "'eventTime' was null");
        checkNotNull(comments, "'comments' was null");
        return new Event(owner, partner, eventTime, comments);
    }

    public User getOwner() {
        return owner;
    }

    public User getPartner() {
        return partner;
    }

    public String getComments() {
        return comments;
    }

    public String getAddress() {
        return partner.getAddress();
    }

    public String getDate() {
        return eventTime.getDate();
    }

    public String getTime() {
        return eventTime.getTime();
    }

}
