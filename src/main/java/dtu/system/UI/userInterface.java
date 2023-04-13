package dtu.system.UI;

import dtu.system.app.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class userInterface {
    public static void main(String[] args) {
        // Daniel
        Application app = new Application();
        Scanner terminal = new Scanner(System.in);
        boolean isExitChosen = false;
        while (!isExitChosen) {
            printLoginMenu();
            System.out.println("Please choose an item from the menu list");
            int input = getIntInput(terminal,2,"The number has to correspond to one of the menu items.");
            if (input == 1) {
                String initials = terminal.next();
                app.logIn(initials);
                // Load next menu
            } else if (input == 2) {
                isExitChosen = true;
            }
        }
        System.out.println("Program terminated.");
    }
    public static int getIntInput(Scanner terminal,int numberOfMenuItems,String message) {
        // Check for a valid number
        while(!terminal.hasNextInt()) {
            System.out.println(message);
            System.out.println();
        }
        return terminal.nextInt();
    }
    public static void printLoginMenu() {
        System.out.println("1. Login");
        System.out.println("2. Exit program");
        System.out.println();
    }
    public static void printMainMenu() {
        System.out.println("1. Add a worker");
        System.out.println("2. Create a project");
        System.out.println("3. Access a project");
        System.out.println("4. Register working hours");
        System.out.println("5. Logout");
        System.out.println();
    }

    public static void printProjectMenu() {
        System.out.println("1. Edit project");
        System.out.println("2. Add an activity");
        System.out.println("3. Access an acivity");
        System.out.println();
    }

    public static void printActivityMenu() {

    }
}
