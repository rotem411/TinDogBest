package com.yodog;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class BackendSimulator {

    private HashMap<String, User> users = new HashMap<>();
    private HashMap<String, Task> canTasks = new HashMap<>();
    private HashMap<String, Task> needTasks = new HashMap<>();

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void signUp(User newUser) {
        // todo check is user exist

        if (newUser != null) {
            users.put(newUser.getName(), newUser);
        }

        // todo throw exception if null/invalid data
    }

    public Boolean signIn(String userName, String password) {
        if (userName == null)
            throw new NoSuchElementException("Expected userName, got Null");

        if (password == null)
            throw new NoSuchElementException("Expected password, got Null");

        User user = users.get(userName);
        return user != null && password.equals(user.getPassword());
    }

    //    + addTask(Task can, Task need)

//    + findMatch(Task can, Task need)
//    + viewProfile(User user)
//    + markDone(Task task)
//    + rateUser(User user, int rate)
//    + editTask(Task task)
//    + removeTask(Task task)

}
