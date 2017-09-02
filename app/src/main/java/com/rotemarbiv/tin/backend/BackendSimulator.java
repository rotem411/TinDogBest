package com.rotemarbiv.tin.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackendSimulator implements Serializable{

    private static final boolean UPCOMING = true;

    private static final boolean PENDING = false;

    private static Random random = new Random();

    private List<User> users = new ArrayList<>();

    private static BackendSimulator instance = new BackendSimulator();

    public static BackendSimulator getInstance() {
        return instance;
    }

    private BackendSimulator() {
        initDB();
    }

    public void initDB() {
        users.add(User.of("no partner", "no email"));
        users.add(User.of("Julian", "julian@gmail.com"));
        users.add(User.of("Kobi", "12kobi@walla.co.il"));
        users.add(User.of("Galgal", "tirtir@patzi.shmenki"));
        users.add(User.of("Rotem", "rotem.ar@walla.co.il"));
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
            task = new Task(needTime, canTime, owner, users.get(0), PENDING);
        } else {
            User partner = users.get(match);
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

    public void signUp(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public ArrayList<Task> signIn(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user.getDashboard().getTasks();
            }
        }
        return null;  // return something that makes sense instead of null
    }

}
