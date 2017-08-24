package com.yodog;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * Created by Omri on 8/15/2017
 */
public class BackendSimulatorTest {

    @Test
    public void testSignUp_ValidUser() throws Exception {
        User user = createUser();
        BackendSimulator backendSimulator = new BackendSimulator();
        backendSimulator.signUp(user);

        assertThat(backendSimulator.getUsers().contains(user), is(true));
    }

    @Test
    public void testSignIn_ExistingUser() throws Exception {
        User user = createUser();
        BackendSimulator backendSimulator = new BackendSimulator();
        backendSimulator.signUp(user);
        Dashboard userDashboard = backendSimulator.signIn(user.getName(), user.getPassword());

        assertThat(userDashboard, is(user.getDashboard()));
    }

    @Test
    public void testSignIn_NotExistingUser() throws Exception {
        BackendSimulator backendSimulator = new BackendSimulator();
        Dashboard userDashboard = backendSimulator.signIn("gal", "daknikim");

        assertNull(userDashboard);
    }

    @Test
    public void testFindMatch_ValidInput() throws Exception {
        BackendSimulator backendSimulator = new BackendSimulator();

        User owner = createUser();
        TaskTime needTime = new TaskTime(2, 3);
        TaskTime canTime = new TaskTime(3, 2);
        int expectedTasksCount = owner.getDashboard().getTasks().size() + 1;

        Dashboard match = backendSimulator.findMatch(needTime, canTime, owner);

        assertThat(match.getTasks().size(), is(expectedTasksCount));
    }

    @Test
    public void testFlow_SignUp_NewTask() throws Exception {
        BackendSimulator backendSimulator = new BackendSimulator();
        User owner = createUser();
        backendSimulator.signUp(owner);

        TaskTime needTime = new TaskTime(2, 3);
        TaskTime canTime = new TaskTime(3, 2);
        Dashboard ownerDashboard = backendSimulator.findMatch(needTime, canTime, owner);

        ArrayList<Task> ownerTasks = ownerDashboard.getTasks();
        if (!ownerTasks.get(ownerTasks.size() - 1).getStatus()) {
            assertThat(ownerTasks.get(ownerTasks.size() - 1).getPartner(), is(backendSimulator.getUsers().get(0)));
        } else {
            assertThat(ownerTasks.get(ownerTasks.size() - 1).getNeedTime(), is(needTime));
            assertThat(ownerTasks.get(ownerTasks.size() - 1).getCanTime(), is(canTime));
        }
    }

    @Test
    public void testFlow_TaskDone_Rate() throws Exception {
        BackendSimulator backendSimulator = new BackendSimulator();
        User owner = createUser();
        backendSimulator.signUp(owner);

        TaskTime needTime = new TaskTime(2, 3);
        TaskTime canTime = new TaskTime(3, 2);
        User partner = backendSimulator.getUsers().get(1);
        Task task = new Task(needTime, canTime, owner, partner, true);
        owner.getDashboard().getTasks().add(task);
        int expectedTaskCount = owner.getDashboard().getTasks().size() - 1;
        int expectedRatesCount = partner.getRates().size() + 1;

        backendSimulator.markDone(task, 3);

        assertThat(owner.getDashboard().getTasks().size(), is(expectedTaskCount));
        assertThat(partner.getRates().size(), is(expectedRatesCount));
    }

    private User createUser() {
        User user = new User();
        user.setName("gal");
        user.setPassword("daknikim");
        return user;
    }

}
