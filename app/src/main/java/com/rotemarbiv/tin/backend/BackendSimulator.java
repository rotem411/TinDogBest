package com.rotemarbiv.tin.backend;

import com.rotemarbiv.tin.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

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
                        "Shamai 5, Jerusalem",
                        "julian@gmail.com",
                        "Bob", "L", givenRates));

        users.add(User.createUser("Kobi Zach",
                        "1234",
                        "08333666",
                        "Loyd George 2, Jerusalem",
                        "12kobi@walla.co.il",
                        "Tooki", "M",givenRates));
        givenRates.add(3);
        givenRates.add(3);
        users.add(User.createUser("Dor Cohen",
                        "1234",
                        "0551239876",
                        "Ha'Banai 4, Jerusalem",
                        "dor.cohen@gmail.com",
                        "Loulou", "L",givenRates));
        givenRates.remove(3);
        givenRates.add(4);

        users.add(User.createUser("Rotem Arbiv",
                "1234",
                "0522418773",
                "Narkis 10, Jerusalem",
                "rotem.rbv@gmail.com",
                "Coco", "S",givenRates));
        users.add(User.createUser("Gal Nachmana",
                "1234",
                "0551239876",
                "Ben Yehuda 9, Jerusalem",
                "gal.nachmana@gmail.com",
                "Messie", "L",givenRates));
        users.add(User.createUser("Laure Scemama",
                "1234",
                "0548085515",
                "Ha'Banai 4, Jerusalem",
                "laurescemama@gmail.com",
                "Doggy", "L",givenRates));
        users.get(4).personPic = R.drawable.rotem_p;
        users.get(5).personPic = R.drawable.gal_p;
        users.get(6).personPic = R.drawable.laure_p;

        users.get(4).dogPic = R.drawable.dog1;
        users.get(5).dogPic = R.drawable.dog2;
        users.get(6).dogPic = R.drawable.dog3;
        Event event = Event.createEvent(getUser("rotem.rbv@gmail.com"), getUser("gal.nachmana@gmail.com"), EventTime.createEventTime("7/10/17", "morning"), "");
        getUser("rotem.rbv@gmail.com").getDashboard().addUserEvent(event);
        getUser("gal.nachmana@gmail.com").getDashboard().addDogEvent(event);
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
            owner.getDashboard().addPendingEvent(dogEvent);
            return null;
        } else {
            User partner = users.get(match);

            while (partner.getEmail().equals(owner.getEmail())){ // can't match selfUser with himself
                match = random.nextInt(users.size());
                partner = users.get(match);
            }

            Event ownerDogEvent = Event.createEvent(partner, owner, needTime, "");
            Event ownerEvent = Event.createEvent(owner, partner, canTime, "");

            Event partnerDogEvent = Event.createEvent(owner, partner, canTime, "");
            Event partnerEvent = Event.createEvent(partner, owner, needTime, "");

            owner.getDashboard().addDogEvent(ownerDogEvent);
            owner.getDashboard().addUserEvent(ownerEvent);

            partner.getDashboard().addUserEvent(partnerEvent);
            partner.getDashboard().addDogEvent(partnerDogEvent);

            return Match.createMatch(ownerEvent, ownerDogEvent);
        }
    }

    public void removeEvent(Event event) {
        event.getOwner().getDashboard().removeUserEvent(event);
        event.getPartner().getDashboard().removeDogEvent(event);
    }

    public User signUp(String name, String password, String dogName, String dogSize, String address, String phone, String email) {
        User user = User.createUser(name, password, phone, address, email, dogName, dogSize);
        users.add(user);
        return user;
    }

    public User getUser(String email) {
        for (User user : users){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public User signIn(String email, String password) {
        User user = getUser(email.toLowerCase());
        if(user != null) {
            if (user.getPassword().equals(password))
                return user;
        }
        return null;  // return something that makes sense instead of null
    }

}
