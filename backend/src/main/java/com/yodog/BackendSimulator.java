package com.yodog;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

class BackendSimulator {

    private HashMap<String, User> users = new HashMap<>();
    private HashMap<Long, Task> canTasks = new HashMap<>();  // todo maybe remove
    private HashMap<Long, Task> needTasks = new HashMap<>();
    private HashMap<String, List<Task>> matchedCan = new HashMap<>();
    private HashMap<String, List<Task>> matchedNeed = new HashMap<>();
    private HashMap<String, List<Task>> pendingCan = new HashMap<>();
    private HashMap<String, List<Task>> pendingNeed = new HashMap<>();
    private static Random random = new Random();

    HashMap<String, User> getUsers() {
        return users;
    }

    HashMap<Long, Task> getCanTasks() {
        return canTasks;
    }

    HashMap<Long, Task> getNeedTasks() {
        return needTasks;
    }

    void signUp(User newUser) {
        // todo check is user exist

        if (newUser != null)
            users.put(newUser.getName(), newUser);

        // todo throw exception if null/invalid data
    }

    Dashboard signIn(String userName, String password) {
        if (userName == null)
            throw new NoSuchElementException("Expected userName, got Null");

        if (password == null)
            throw new NoSuchElementException("Expected password, got Null");

        User user = users.get(userName);
        if (user == null)  // login fail
            return null;

        return generateDashboard(user);
    }

    Dashboard addTask(Task canTask, Task needTask) {
        if (canTask == null)
            throw new NoSuchElementException("Expected canTask, got Null");

        if (needTask == null)
            throw new NoSuchElementException("Expected needTask, got Null");

        if (System.currentTimeMillis() > canTask.getTime() || System.currentTimeMillis() > needTask.getTime())
            return generateDashboard(canTask.getOwner());  // todo throw an exception that makes sense

        // todo find match, return dashboard with matches found or with no update
        return findMatch(canTask, needTask);
    }

    private Dashboard generateDashboard(User user) {
        return new Dashboard(pendingCan.get(user.getName()), pendingNeed.get(user.getName()),
                matchedCan.get(user.getName()), matchedNeed.get(user.getName()), user);
    }

    User viewProfile(String username) {  // todo test
        if (username == null)
            throw new NoSuchElementException("Expected username, got null");

        return users.get(username);
    }

    void rateUser(User user, int rate) {
        // todo validate params not null
        user.setRating((user.getRating() + rate) / 2);  // todo make rating better
    }

    Dashboard findMatch(Task can, Task need) {
        boolean found = random.nextBoolean();
        String userName = can.getOwner().getName();

        if (found) {
            matchedCan.get(userName).add(can);  // todo add every new user to the maps, and init it's task list!!
            matchedNeed.get(userName).add(need);
        } else {
            pendingCan.get(userName).add(can);  // todo add every new user to the maps, and init it's task list!!
            pendingNeed.get(userName).add(need);
        }

        return generateDashboard(can.getOwner());
    }

//    + markDone(Task task)
//    + editTask(Task task)
//    + removeTask(Task task)

}
