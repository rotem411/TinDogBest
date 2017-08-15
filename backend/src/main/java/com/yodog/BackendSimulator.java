package com.yodog;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

class BackendSimulator {

    private HashMap<String, User> users = new HashMap<>();
    private HashMap<Long, Task> canTasks = new HashMap<>();
    private HashMap<Long, Task> needTasks = new HashMap<>();

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

    boolean signIn(String userName, String password) {
        if (userName == null)
            throw new NoSuchElementException("Expected userName, got Null");

        if (password == null)
            throw new NoSuchElementException("Expected password, got Null");

        User user = users.get(userName);
        return user != null && password.equals(user.getPassword());
    }

    boolean addTask(Task canTask, Task needTask) {
        if (canTask == null)
            throw new NoSuchElementException("Expected canTask, got Null");

        if (needTask == null)
            throw new NoSuchElementException("Expected needTask, got Null");

        if (System.currentTimeMillis() > canTask.getTime() || System.currentTimeMillis() > needTask.getTime())
            return false;  // todo throw an exception that makes sense

        canTasks.put(canTask.getTime(), canTask);
        needTasks.put(needTask.getTime(), needTask);
        return true;
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

    List<Task> findMatch(Task can, Task need) {
        return null;
    }

//    + markDone(Task task)
//    + editTask(Task task)
//    + removeTask(Task task)

}
