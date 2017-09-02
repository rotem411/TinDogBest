package com.rotemarbiv.tin.backend;

import java.io.Serializable;

/**
 * Created by Omri on 8/15/2017
 */

public class Task implements Serializable{

    private EventTime needTime;
    private EventTime canTime;
    private User owner;
    private User partner;
    private boolean ownerDone = false;
    private boolean partnerDone = false;
    private boolean status;

    public Task(EventTime needTime, User owner, boolean status) {
        this.needTime = needTime;
        this.owner = owner;
        this.status = status;
    }

    public Task(EventTime needTime, EventTime canTime, User owner, User partner, boolean status) {
        this.needTime = needTime;
        this.canTime = canTime;
        this.owner = owner;
        this.partner = partner;
        this.status = status;
    }

    public EventTime getNeedTime() {
        return needTime;
    }

    public EventTime getCanTime() {
        return canTime;
    }

    public User getOwner() {
        return owner;
    }

    public boolean isOwnerDone() {
        return ownerDone;
    }

    public void setOwnerDone(boolean ownerDone) {
        this.ownerDone = ownerDone;
    }

    public User getPartner() {
        return partner;
    }

    public void setPartner(User partner) {
        this.partner = partner;
    }

    public boolean isPartnerDone() {
        return partnerDone;
    }

    public void setPartnerDone(boolean partnerDone) {
        this.partnerDone = partnerDone;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
