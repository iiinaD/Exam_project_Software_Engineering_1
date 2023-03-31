package dtu.system.commands;

import dtu.system.app.Application;

public class CreateProject implements Command{
    @Override
    public void execute(Application app) {
        System.out.println("New project has been created");
    }
}
