package com.rotemarbiv.tin.backend;

import java.util.ArrayList;

/**
 * Created by Omri on 8/16/2017
 */

public class Dashboard {

    private ArrayList<Task> tasks = new ArrayList<>();

    public Dashboard() {
    }

    public Dashboard(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
