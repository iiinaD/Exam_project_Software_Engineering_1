package dtu.system.commands;

public class Login implements Command{
    @Override
    public void execute() {
        System.out.println("Worker is logged in");
    }
}
