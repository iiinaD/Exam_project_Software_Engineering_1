package dtu.system.unit_tests.junit;
import static org.junit.jupiter.api.Assertions.*;

import dtu.system.app.Application;
import dtu.system.app.OperationNotAllowedException;
import dtu.system.domain.HalfHours;
import dtu.system.domain.Project;
import dtu.system.domain.Worker;
import dtu.system.domain.Activity;
import org.junit.jupiter.api.Test;

public class SystematicTests {

    private Worker worker1;
    private Worker worker2;
    private Worker worker3;
    private Application app;
    private Project project;
    private Activity activity;


    @Test
    public void isProjectLeaderTest() throws OperationNotAllowedException {
        // Daniel
        app = new Application();
        worker1 = new Worker("daha");
        worker2 = new Worker("jodl");
        worker3 = new Worker("enl");
        app.addNewWorker(worker1);
        app.addNewWorker(worker2);
        app.addNewWorker(worker3);

        app.logIn("jodl");
        app.createProject("Website backend rework",worker1);
        project = app.getProjectWithNumber(23001);
        app.addActivityToProject(project,"Development","Development of the website backend");
        activity = app.getActivityFromProject(23001,"23001-001");

        String message = "";

        // execution path A
        try {
            app.addWorkerToActivity(project.getProjectNumber(), "23001-001", "enl");
        } catch (OperationNotAllowedException e) {
            message = e.getMessage();
        }
        assertEquals("Only project leaders can assign workers to activities.",message);

        app.logOut();
        app.logIn("daha");

        // excecution path C
        app.addWorkerToActivity(project.getProjectNumber(), "23001-001", "enl");
        assertTrue(activity.isWorkerAssigned("enl"));

        // excecution path B
        try {
            app.addWorkerToActivity(project.getProjectNumber(), "23001-001", "enl");
        } catch (OperationNotAllowedException e) {
            message = e.getMessage();
        }
        assertEquals("enl is already in the list!",message);


    }

    @Test
    public void incrementTest() {
        // Jonas
        // White box test of the method increment in HalfHours
        HalfHours A = new HalfHours(0,0);
        HalfHours B = new HalfHours(0,0);
        HalfHours C = new HalfHours(0,0);
        HalfHours D = new HalfHours(0,0);
        HalfHours E = new HalfHours(0,0);
        HalfHours F = new HalfHours(0,0);

        A.increment(0, -13);
        assertEquals(0, A.getTime());

        B.increment(1, 44);
        assertEquals(1.5, B.getTime());

        C.increment(2, -60);
        assertEquals(1, C.getTime());

        D.increment(-1, 60);
        assertEquals(0, D.getTime());

        E.increment(-2, -59);
        assertEquals(0, E.getTime());

        F.increment(0, -30);
        assertEquals(0, F.getTime());
    }

    //validProjectNumberTest test/////////////////////////////////////
    //adapted from ChatGPT response
    //Gee
    @org.junit.Test
    public void testValidProjectNumber() throws OperationNotAllowedException {
        app = new Application(); //instantiate new app
        // Arrange
        int number = 23001; // valid project number

        // Act
        boolean result = app.validProjectNumberTest(number);

        // Assert
        assertTrue(result);
    }

    @org.junit.Test
    public void testInvalidProjectNumberTooSmall() {
        app = new Application(); //instantiate new app
        // Arrange
        int number = 2300; // invalid project number, too small

        // Act & Assert
        assertThrows(OperationNotAllowedException.class, () -> {
            app.validProjectNumberTest(number);
        });
    }

    @org.junit.Test
    public void testInvalidProjectNumberTooLarge() {
        app = new Application(); //instantiate new app
        // Arrange
        int number = 230001; // invalid project number, too large

        // Act & Assert
        assertThrows(OperationNotAllowedException.class, () -> {
            app.validProjectNumberTest(number);
        });
    }
    //END OF validProjectNumberTest TEST/////////////////

    @Test
    public void addNewWorkerTest() throws OperationNotAllowedException {
        // Danny
        // white box test for the 'addNewWorker' method of the application class
        app = new Application();

        // *** A ***
        // setup
        Worker workerA = new Worker("jodl");
        app.addNewWorker(workerA);

        // test
        assertThrows(OperationNotAllowedException.class, () -> { app.addNewWorker(workerA); });

        // *** B ***
        // setup
        Worker workerB = new Worker("dahala");

        // test
        assertThrows(OperationNotAllowedException.class, () -> { app.addNewWorker(workerB); });

        // *** C ***
        // setup
        Worker workerC = new Worker("t5!n");

        // test
        assertThrows(OperationNotAllowedException.class, () -> { app.addNewWorker(workerC); });

        // *** D ***
        // setup
        Worker workerD = new Worker("hub");
        app.addNewWorker(workerD);

        // test
        assertTrue(app.getWorkerList().contains(workerD));
    }
}
