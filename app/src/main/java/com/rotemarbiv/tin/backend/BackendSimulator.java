package com.rotemarbiv.tin.backend;

import java.io.Serializable;
import java.util.ArrayList;
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
                User.createUser("Julian",
                        "password",
                        "052345678",
                        Address.createAddress("Shamai 5, Jerusalem"),
                        "julian@gmail.com",
                        Dog.createDog("Bob", "L")));
        users.put("12kobi@walla.co.il",
                User.createUser("Kobi",
                        "1234",
                        "08333666",
                        Address.createAddress("Loyd George 2, Jerusalem"),
                        "12kobi@walla.co.il",
                        Dog.createDog("Tooki", "M")));
        users.put("tirtir@patzi.shmenki",
                User.createUser("Galgal",
                        "1234",
                        "0551239876",
                        Address.createAddress("Ha'Banai 4, Jerusalem"),
                        "tirtir@patzi.shmenki",
                        Dog.createDog("Loulou", "L")));
    }

    void rateUser(User user, int rate) {
        if (user == null)
            return;

        user.getRates().add(rate);
    }

    Match findMatch(EventTime needTime, EventTime canTime, User owner) {
        int match = random.nextInt(users.size());
        ArrayList<String> userEmails = new ArrayList<>(users.keySet());

        if (match == 0) {
            Event dogEvent = Event.createEvent(owner, users.get("no mail"), canTime, "");
            owner.getDashboard().getPendingEvents().addEvent(dogEvent);
            return null;
        } else {
            User partner = users.get(userEmails.get(match));
            Event ownerDogEvent = Event.createEvent(owner, partner, needTime, "");
            Event ownerEvent = Event.createEvent(partner, owner, canTime, "");

            Event partnerDogEvent = Event.createEvent(partner, owner, canTime, "");
            Event partnerEvent = Event.createEvent(owner, partner, needTime, "");

            owner.getDashboard().getDogEvents().addEvent(ownerDogEvent);
            owner.getDashboard().getUserEvents().addEvent(ownerEvent);

            partner.getDashboard().getUserEvents().addEvent(partnerEvent);
            partner.getDashboard().getDogEvents().addEvent(partnerDogEvent);

            return Match.createMatch(ownerEvent, ownerDogEvent);
        }
    }

    public void removeEvent(Event event) {
        event.getOwner().getDashboard().getUserEvents().removeEvent(event);
        event.getPartner().getDashboard().getDogEvents().removeEvent(event);
    }

    public User signUp(String name, String password, String dogName, String dogSize, String address, String phone, String email) {
        Dog dog = Dog.createDog(dogName, dogSize);
        Address theAddress = Address.createAddress(address);
        User user = User.createUser(name, password, phone, theAddress, email, dog);
        users.put(user.getEmail(), user);
        return user;
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
