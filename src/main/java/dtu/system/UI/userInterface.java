package dtu.system.UI;

import dtu.system.app.Application;
import dtu.system.commands.*;

import java.awt.desktop.AppForegroundListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class userInterface {

    public static void main(String[] args){
        Map<String, Command> commands = new HashMap<>();
        loadCommands(commands);

        Scanner console = new Scanner(System.in);
        Application app = new Application();

        System.out.println("System has started.");
        System.out.println("For list of commands type \"help\"");

        while (true) {
            System.out.print("Please enter a command:\n > ");
            String input = console.nextLine();
            Command command = commands.get(input);

            if (exitTest(input)) {
                break;
            }

            if (command != null) {
                command.execute(app);
            } else {
                System.out.println("Unknown command for list of valid commands type \"help\"");
            }
        }


        console.close();
    }

    public static boolean exitTest(String input){
        return (input.equalsIgnoreCase("Exit") || input.equalsIgnoreCase("Quit"));
    }

    private static void loadCommands(Map<String, Command> commands) {
        commands.put("login", (Command) new Login());
        commands.put("create project", (Command) new CreateProject());
        commands.put("add worker", (Command) new AddWorker());
    }
}
