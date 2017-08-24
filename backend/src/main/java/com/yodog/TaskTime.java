package com.yodog;

/**
 * Created by Omri on 8/24/2017
 */

class TaskTime {

    private int dayOfWeek;
    private int chunk;

    public TaskTime(int dayOfWeek, int chunk) {
        this.dayOfWeek = dayOfWeek;
        this.chunk = chunk;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getChunk() {
        return chunk;
    }
}
