package dtu.system.commands;

public class CreateProject implements Command{
    @Override
    public void execute() {
        System.out.println("New project has been created");
    }
}
