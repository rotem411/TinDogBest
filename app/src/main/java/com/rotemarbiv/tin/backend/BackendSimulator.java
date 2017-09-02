package com.rotemarbiv.tin.backend;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class BackendSimulator implements Serializable {

    private static final boolean UPCOMING = true;

    private static final boolean PENDING = false;

    private static Random random = new Random();

    private HashMap<String, User> users = new HashMap<>();

    private static BackendSimulator instance = new BackendSimulator();

    public static BackendSimulator getInstance() {
        return instance;
    }

    private BackendSimulator() {
        initDB();
    }

    private void initDB() {
        users.put("no mail", User.of("no partner", "no email"));
        users.put("julian@gmail.com", User.of("Julian", "julian@gmail.com"));
        users.put("12kobi@walla.co.il", User.of("Kobi", "12kobi@walla.co.il"));
        users.put("tirtir@patzi.shmenki", User.of("Galgal", "tirtir@patzi.shmenki"));
        users.put("rotem.ar@walla.co.il", User.of("Rotem", "rotem.ar@walla.co.il"));
    }

    void rateUser(User user, int rate) {
        if (user == null)
            return;

        user.getRates().add(rate);
    }

    Dashboard findMatch(TaskTime needTime, TaskTime canTime, User owner) {
        Dashboard userDashboard = owner.getDashboard();
        int match = random.nextInt(users.size());
        Task task;
        if (match == 0) {
            task = new Task(needTime, canTime, owner, users.get("no mail"), PENDING);
        } else {
            User partner = users.get(match);  // FIXME: 9/2/2017 match is an int, we need the email address
            task = new Task(needTime, canTime, owner, partner, UPCOMING);
            partner.getDashboard().getTasks().add(new Task(canTime, needTime, partner, owner, UPCOMING));
        }

        userDashboard.getTasks().add(task);  // update dashboard
        return userDashboard;
    }

    public void removeTask(User owner, Task task) {
        owner.getDashboard().getTasks().remove(task);
    }

    public void markDone(Task task, int partnerRating) {
        task.getPartner().rate(partnerRating);
        task.getOwner().getDashboard().getTasks().remove(task);
    }

    public User signUp(String name, String password, String dogName, String dogSize, String address, String phone, String email) {
        Dog dog = new Dog(dogName, dogSize);
        Address theAddress = Address.createAddress(address);
        User user = new User(name, password, phone, theAddress, email, dog);
        users.put(user.getEmail(), user);
        return user;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public User signIn(String email, String password) {
        User user = users.get(email.toLowerCase());
        if (user.getPassword().equals(password))
            return user;
        return null;  // return something that makes sense instead of null
    }

}
