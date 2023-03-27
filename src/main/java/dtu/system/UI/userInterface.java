package dtu.system.UI;

import dtu.system.app.Application;

import java.awt.desktop.AppForegroundListener;
import java.util.Scanner;

public class userInterface {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);

        Application app = new Application();

        System.out.println("System has started.");
        String input = console.next();

        while (true) {

            
            if (exitTest(input)) {
                break;
            }
        }


        console.close();
    }

    public static boolean exitTest(String input){
        return !(input.equalsIgnoreCase("Exit") || input.equalsIgnoreCase("Quit"));
    }
}
