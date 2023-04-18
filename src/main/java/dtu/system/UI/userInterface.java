package dtu.system.UI;

import dtu.system.app.Application;
import dtu.system.app.OperationNotAllowedException;
import dtu.system.domain.Project;
import dtu.system.domain.Worker;

import java.util.Scanner;

public class userInterface {
    public static void main(String[] args) throws OperationNotAllowedException {
        // Daniel
        Application app = new Application();
        Scanner terminal = new Scanner(System.in);
        boolean isExitChosen = false;
        while (!isExitChosen) {
            System.out.println();
            System.out.println("----- Login menu -----");
            int numMenuItems = printLoginMenu();
            System.out.print("Please choose an item from the menu list\n> ");
            int input = getIntInput(terminal,numMenuItems,"The number has to correspond to one of the menu items.");
            if (input == 1) {
                System.out.print("Please input your worker initials\n> ");
                String initials = terminal.next();
                try {
                    app.logIn(initials);
                    System.out.println("Worker " + initials + " is now logged in");
                    // Load main menu
                    mainMenu(app, terminal);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (input == 2) {
                System.out.print("Please input the worker's initials\n> ");
                String initials = terminal.next();
                try {
                    Worker newWorker = new Worker(initials);
                    app.addNewWorker(newWorker);
                    System.out.println("The worker " + initials + " has been added\n");
                } catch (OperationNotAllowedException | IllegalArgumentException e) {
                    System.out.println(e.getMessage() + "\n");
                }

            } else if (input == 3) {
                isExitChosen = true;
            }
        }
        System.out.println("Program terminated.");
    }
    public static void mainMenu(Application app, Scanner terminal) {
        // Daniel
        while (app.getLoggedInStatus()) {
            System.out.println();
            System.out.println("----- Main menu -----");
            int numMenuItems = printMainMenu();
            System.out.print("Please choose an item from the menu list\n> ");
            int input = getIntInput(terminal,numMenuItems,"The number has to correspond to one of the menu items.");
            if (input == 1) {
                System.out.print("Please input the name of the project\n> ");
                String garbage = terminal.nextLine();
                String projectName = terminal.nextLine();
                System.out.print("\nPlease input the initials of the project leader (if not yet known enter 1)\n> ");
                String projectLeader = terminal.next();
                try {
                    Project createdProject;
                    if (projectLeader.equals("1")) {
                        createdProject = app.createProject(projectName);
                    } else {
                        createdProject = app.createProject(projectName, app.getWorkerWithInitials(projectLeader));
                    }
                    System.out.println("Project with number " + createdProject.getProjectNumber() + " has been created");

                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (input == 2) {
                System.out.print("Please input the id of the project\n> ");
                int projectNumber = terminal.nextInt();
                try {
                    Project accessProject = app.getProjectWithNumber(projectNumber);
                    System.out.println("Project has been created");
                    projectMenu(app,terminal,accessProject);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (input == 3) {

            } else if (input == 4) {
                app.logOut();
                System.out.println("Worker has been logged out");
            }
        }
    }

    private static void projectMenu(Application app, Scanner terminal, Project project) {
        boolean returnToMainMenu = false;
        while (!returnToMainMenu) {
            System.out.println();
            System.out.println("----- " + project.getProjectName() + " (" + project.getProjectNumber() + ")" + " -----");
            int numMenuItems = printProjectMenu();
            System.out.print("Please choose an item from the menu list\n> ");
            int input = getIntInput(terminal,numMenuItems,"The number has to correspond to one of the menu items.");
            if (input == 1) {

            } else if (input == 2) {

            } else if (input == 3) {

            } else if (input == 4) {

            } else if (input == 5) {
                returnToMainMenu = true;
            }
        }
    }

    public static int getIntInput(Scanner terminal,int numberOfMenuItems,String message) {
        // Daniel
        // Check for a valid number
        int choice;
        while (true) {
            boolean intInput = terminal.hasNextInt();
            if (intInput) {
                choice = terminal.nextInt();
                if (choice <= numberOfMenuItems && choice > 0) {
                    break;
                }
            } else {
                terminal.next();
            }
            System.out.println("\n" + message);
            System.out.print("Please try again: ");
        }
        System.out.println();
        return choice;
    }
    public static int printLoginMenu() {
        // Daniel
        System.out.println("1. Log in");
        System.out.println("2. Add new worker");
        System.out.println("3. Exit program");
        return 3;
    }
    public static int printMainMenu() {
        // Daniel
        System.out.println("1. Create a project");
        System.out.println("2. Access a project");
        System.out.println("3. Register working hours");
        System.out.println("4. Logout and return to login screen");
        return 4;
    }

    public static int printProjectMenu() {
        // Daniel
        System.out.println("1. Edit project");
        System.out.println("2. Add an activity");
        System.out.println("3. Access an acivity");
        System.out.println("4. Print project info");
        System.out.println("5. Return to main menu");
        return 5;
    }

    public static void printActivityMenu() {
        // Daniel

    }
}
