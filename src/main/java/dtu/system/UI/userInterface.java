package dtu.system.UI;

import java.util.Scanner;

public class userInterface {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);

        System.out.println("System has started.");
        String input = console.next();

        while (exitTest(input)){

        }


        console.close();
    }

    public static boolean exitTest(String input){
        return !input.equalsIgnoreCase("Exit");
    }
}
