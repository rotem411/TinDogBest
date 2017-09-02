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
        users.put("julian@gmail.com",
                new User("Julian",
                        "password",
                        "052345678",
                        Address.createAddress("Shamai 5, Jerusalem"),
                        "julian@gmail.com",
                        Dog.createDog("Bob", "L")));
        users.put("12kobi@walla.co.il",
                new User("Kobi",
                        "1234",
                        "08333666",
                        Address.createAddress("Loid George 2, Jerusalem"),
                        "12kobi@walla.co.il",
                        Dog.createDog("Tooki", "M")));
        users.put("tirtir@patzi.shmenki",
                new User("Galgal",
                        "1234",
                        "0551239876",
                        Address.createAddress("Ha'Banai 4, Jerusalem"),
                        "tirtir@patzi.shmenki",
                        Dog.createDog("loulou", "L")));
    }

    void rateUser(User user, int rate) {
        if (user == null)
            return;

        user.getRates().add(rate);
    }

    Dashboard findMatch(EventTime needTime, EventTime canTime, User owner) {
        Dashboard userDashboard = owner.getDashboard();
        int match = random.nextInt(users.size());
        Task task;
        if (match == 0) {
            task = new Task(needTime, canTime, owner, users.get("no mail"), PENDING);
        } else {
            User partner = users.get(match);  // FIXME: 9/2/2017 match is an int, we need the email address
            task = new Task(needTime, canTime, owner, partner, UPCOMING);
//            partner.getDashboard().getTasks().add(new Task(canTime, needTime, partner, owner, UPCOMING));
        }

//        userDashboard.getTasks().add(task);  // update dashboard
        return userDashboard;
    }

    public void removeTask(User owner, Task task) {
//        owner.getDashboard().getTasks().remove(task);
    }

    public void markDone(Task task, int partnerRating) {
        task.getPartner().rate(partnerRating);
//        task.getOwner().getDashboard().getTasks().remove(task);
    }

    public User signUp(String name, String password, String dogName, String dogSize, String address, String phone, String email) {
        Dog dog = Dog.createDog(dogName, dogSize);
        Address theAddress = Address.createAddress(address);
        User user = new User(name, password, phone, theAddress, email, dog);
        users.put(user.getEmail(), user);
        return user;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public User getUser(String email) {
        return users.get(email);
    }

    public User signIn(String email, String password) {
        User user = users.get(email.toLowerCase());
        if (user.getPassword().equals(password))
            return user;
        return null;  // return something that makes sense instead of null
    }

}
