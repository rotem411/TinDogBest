package com.rotemarbiv.tin.backend;

/**
 * Created by Omri on 8/24/2017
 */

class TaskTime {

    private int dayOfWeek;
    private String chunk;

    public TaskTime(int dayOfWeek, String chunk) {
        this.dayOfWeek = dayOfWeek;
        this.chunk = chunk;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public String getChunk() {
        return chunk;
    }
}
