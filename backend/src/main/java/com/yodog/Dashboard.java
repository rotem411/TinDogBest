package com.yodog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omri on 8/16/2017
 */

public class Dashboard {

    private List<Task> pendingCan = new ArrayList<>();
    private List<Task> pendingNeed = new ArrayList<>();
    private List<Task> matchedCan = new ArrayList<>();
    private List<Task> matchedNeed = new ArrayList<>();
    private User user;

    public Dashboard(List<Task> pendingCan, List<Task> pendingNeed, List<Task> matchedCan, List<Task> matchedNeed, User user) {
        this.pendingCan = pendingCan;
        this.pendingNeed = pendingNeed;
        this.matchedCan = matchedCan;
        this.matchedNeed = matchedNeed;
        this.user = user;
    }

    public List<Task> getPendingCan() {
        return pendingCan;
    }

    public List<Task> getPendingNeed() {
        return pendingNeed;
    }

    public List<Task> getMatchedCan() {
        return matchedCan;
    }

    public List<Task> getMatchedNeed() {
        return matchedNeed;
    }

    public User getUser() {
        return user;
    }

}
