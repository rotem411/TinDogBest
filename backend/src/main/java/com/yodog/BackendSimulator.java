package com.yodog;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class BackendSimulator {

    private static final boolean UPCOMING = true;
    private static final boolean PENDING = false;
    private static Random random = new Random();

    private List<User> users = Arrays.asList(
            User.of("no partner"),
            User.of("Julian"),
            User.of("Kobi")
    );

    void rateUser(User user, int rate) {
        if (user == null)
            return;

        user.getRates().add(rate);
    }

    Dashboard findMatch(Task requestTask) {
        User user = requestTask.getOwner();
        Dashboard userDashboard = user.getDashboard();
        int match = random.nextInt(10);
        Task task;
        if (match == 0) {
            task = new Task(requestTask.getNeedTime(), requestTask.getCanTime(), user, users.get(0), PENDING);
        } else {
            User partner = users.get(match);
            task = new Task(requestTask.getCanTime(), requestTask.getNeedTime(), user, partner, UPCOMING);
        }

        userDashboard.getTasks().add(task);  // update dashboard
        return userDashboard;
    }

//    + markDone(Task task)
//    + editTask(Task task)
//    + removeTask(Task task)

}
