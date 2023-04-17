package dtu.system.UI;

import dtu.system.app.Application;
import dtu.system.app.OperationNotAllowedException;
import dtu.system.domain.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class userInterface {
    public static void main(String[] args) throws OperationNotAllowedException {
        // Daniel
        Application app = new Application();
        Scanner terminal = new Scanner(System.in);
        boolean isExitChosen = false;
        while (!isExitChosen) {
            printLoginMenu();
            System.out.print("Please choose an item from the menu list: ");
            int input = getIntInput(terminal,3,"The number has to correspond to one of the menu items.");
            if (input == 1) {
                System.out.print("Please input your worker initials: ");
                String initials = terminal.next();
                try {
                    app.logIn(initials);
                    // Load main menu
                    mainMenu(app, terminal);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (input == 2) {
                System.out.print("Please input the worker's initials: ");
                String initials = terminal.next();
                try {
                    Worker newWorker = new Worker(initials);
                    app.addNewWorker(newWorker);
                    System.out.println("The worker has been added\n");
                } catch (OperationNotAllowedException e) {
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
        while(app.getLoggedInStatus()) {
            printMainMenu();
            System.out.print("Please choose an item from the menu list: ");
            int input = getIntInput(terminal,4,"The number has to correspond to one of the menu items.");
            if (input == 1) {
                System.out.print("Please input the name of the project: ");
                String projectName = terminal.next();
                try {
                    app.createProject(projectName);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            }
        }
        return;
    }
    public static int getIntInput(Scanner terminal,int numberOfMenuItems,String message) {
        // Daniel
        // Check for a valid number
        int choice;
        while(true) {
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
    public static void printLoginMenu() {
        // Daniel
        System.out.println("1. Log in");
        System.out.println("2. Add new worker");
        System.out.println("3. Exit program");
    }
    public static void printMainMenu() {
        // Daniel
        System.out.println("1. Create a project");
        System.out.println("2. Access a project");
        System.out.println("3. Register working hours");
        System.out.println("4. Logout");
    }

    public static void printProjectMenu() {
        // Daniel
        System.out.println("1. Edit project");
        System.out.println("2. Add an activity");
        System.out.println("3. Access an acivity");
        System.out.println();
    }

    public static void printActivityMenu() {
        // Daniel

    }
}
