package dtu.system.commands;

import dtu.system.app.Application;

public class Login implements Command{
    @Override
    public void execute(Application app) {
        System.out.println("Worker is logged in");
    }
}
