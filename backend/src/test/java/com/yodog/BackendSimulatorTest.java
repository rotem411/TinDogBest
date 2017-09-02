package com.yodog;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Omri on 8/15/2017
 */
public class BackendSimulatorTest {

    @Test
    public void testSignUp_ValidUser() throws Exception {
        User user = createUser();
        BackendSimulator backendSimulator = new BackendSimulator();
        backendSimulator.signUp(user);

        assertThat(backendSimulator.getUsers().containsKey("gal"), is(true));
    }

    @Test
    public void testSignIn_ExistingUser() throws Exception {
        User user = createUser();
        BackendSimulator backendSimulator = new BackendSimulator();
        backendSimulator.signUp(user);
        Boolean loginStatus = backendSimulator.signIn(user.getName(), user.getPassword());

        assertTrue(loginStatus);
    }

    @Test
    public void testSignIn_NotExistingUser() throws Exception {
        BackendSimulator backendSimulator = new BackendSimulator();
        Boolean loginStatus = backendSimulator.signIn("gal", "daknikim");

        assertFalse(loginStatus);
    }

    @Test
    public void testAddTask_ValidInput() throws Exception {
        BackendSimulator backendSimulator = new BackendSimulator();
        Task canTask = new Task();
        canTask.setTime(System.currentTimeMillis() + 1000);
        Task needTask = new Task();
        needTask.setTime(System.currentTimeMillis() + 1000);
        backendSimulator.addTask(canTask, needTask);

        assertThat(backendSimulator.getCanTasks().size(), is(1));
        assertThat(backendSimulator.getNeedTasks().size(), is(1));
    }

    private User createUser() {
        User user = new User();
        user.setName("gal");
        user.setPassword("daknikim");
        return user;
    }

}
