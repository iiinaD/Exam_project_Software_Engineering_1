package dtu.system.commands;

import dtu.system.app.Application;

public interface Command {
    void execute(Application app);
}
