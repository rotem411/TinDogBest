package com.rotemarbiv.tin.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static java.lang.System.in;

public class BackendSimulator implements Serializable {

    private static final boolean UPCOMING = true;

    private static final boolean PENDING = false;

    private static Random random = new Random();

    private static ArrayList<User> users = new ArrayList<>();

    private static BackendSimulator instance = new BackendSimulator();

    public static BackendSimulator getInstance() {
        return instance;
    }

    private BackendSimulator() {
        initDB();
    }

    private void initDB() {
        ArrayList<Integer> givenRates = new ArrayList<Integer>();
        givenRates.add(5);
        givenRates.add(4);
        users.add(User.of("no partner", "no email"));
        users.add(User.createUser("Julian Smith",
                            "password",
                        "052345678",
                        Address.createAddress("Shamai 5, Jerusalem"),
                        "julian@gmail.com",
                        "Bob", "L", givenRates));

        users.add(User.createUser("Kobi Zach",
                        "1234",
                        "08333666",
                        Address.createAddress("Loyd George 2, Jerusalem"),
                        "12kobi@walla.co.il",
                        "Tooki", "M",givenRates));
        givenRates.add(3);
        givenRates.add(3);
        users.add(User.createUser("Gal Cohen",
                        "1234",
                        "0551239876",
                        Address.createAddress("Ha'Banai 4, Jerusalem"),
                        "tirtir@patzi.shmenki",
                        "Loulou", "L",givenRates));
        Event event = Event.createEvent(getUser("julian@gmail.com"), getUser("12kobi@walla.co.il"), EventTime.createEventTime("7/10/17", "morning"), "");
        getUser("julian@gmail.com").getDashboard().getUserEvents().addEvent(event);
        getUser("12kobi@walla.co.il").getDashboard().getDogEvents().addEvent(event);
    }

    void rateUser(User user, int rate) {
        if (user == null)
            return;

        user.getRates().add(rate);
    }

    public Match findMatch(EventTime needTime, EventTime canTime, User owner) {
        int match = random.nextInt(users.size());


        if (match == 0) {
            Event dogEvent = Event.createEvent(getUser("no mail"), owner, needTime, "");
            owner.getDashboard().getPendingEvents().addEvent(dogEvent);
            return null;
        } else {
            User partner = users.get(match);

            while (partner.getEmail() == owner.getEmail()){ // can't match selfUser with himself
                match = random.nextInt(users.size());
                partner = users.get(match);
            }

            Event ownerDogEvent = Event.createEvent(partner, owner, needTime, "");
            Event ownerEvent = Event.createEvent(owner, partner, canTime, "");

            Event partnerDogEvent = Event.createEvent(owner, partner, canTime, "");
            Event partnerEvent = Event.createEvent(partner, owner, needTime, "");

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
        Address theAddress = Address.createAddress(address);
        User user = User.createUser(name, password, phone, theAddress, email, dogName, dogSize);
        users.add(user);
        return user;
    }

    public User getUser(String email) {
        for (User user : users){
            if (user.getEmail() == email){
                return user;
            }
        }
        return null;
    }

    public User signIn(String email, String password) {
        User user = getUser(email.toLowerCase());
        if (user.getPassword().equals(password))
            return user;
        return null;  // return something that makes sense instead of null
    }

}
